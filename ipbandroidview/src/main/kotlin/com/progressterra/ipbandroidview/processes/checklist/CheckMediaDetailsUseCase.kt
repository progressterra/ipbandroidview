package com.progressterra.ipbandroidview.processes.checklist

import com.google.gson.Gson
import com.google.gson.annotations.SerializedName
import com.progressterra.ipbandroidapi.api.ipbmediadata.IPBMediaDataService
import com.progressterra.ipbandroidapi.api.ipbmediadata.models.FilterAndSort
import com.progressterra.ipbandroidapi.api.ipbmediadata.models.TypeContent
import com.progressterra.ipbandroidview.entities.Check
import com.progressterra.ipbandroidview.entities.MultisizedImage
import com.progressterra.ipbandroidview.entities.Voice
import com.progressterra.ipbandroidview.pages.checklist.CurrentCheckMedia
import com.progressterra.ipbandroidview.processes.media.FileExplorer
import com.progressterra.ipbandroidview.processes.utils.MakeToastUseCase
import com.progressterra.ipbandroidview.processes.utils.ManageResources
import com.progressterra.ipbandroidview.processes.utils.ObtainAccessToken
import com.progressterra.ipbandroidview.shared.mvi.AbstractTokenUseCase
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import java.io.InputStream

interface CheckMediaDetailsUseCase {

    suspend operator fun invoke(check: Check): Result<CurrentCheckMedia>

    class Base(
        obtainAccessToken: ObtainAccessToken,
        makeToastUseCase: MakeToastUseCase,
        manageResources: ManageResources,
        private val fileExplorer: FileExplorer,
        private val mediaDataService: IPBMediaDataService,
        private val gson: Gson
    ) : AbstractTokenUseCase(obtainAccessToken, makeToastUseCase, manageResources),
        CheckMediaDetailsUseCase {

        override suspend fun invoke(
            check: Check
        ): Result<CurrentCheckMedia> = withToken { token ->
            val voices = mutableListOf<Voice>()
            val pictures = mutableListOf<MultisizedImage>()
            mediaDataService.attachedToEntity(
                token,
                check.id,
                FilterAndSort(
                    emptyList(),
                    null,
                    "",
                    0,
                    300
                )
            ).dataList?.forEach { item ->
                if (item.contentType == TypeContent.IMAGE) {
                    val sizes = gson.fromJson(item.dataJSON, ImageData::class.java).list
                    pictures.add(
                        MultisizedImage(
                            id = item.idUnique!!,
                            local = false,
                            toRemove = false,
                            url = sizes.first { it.sizeType == 1 }.url
                        )
                    )
                } else if (item.contentType == TypeContent.VOICE_DATA && voices.size == 0) {
                    saveAudio(
                        mediaDataService.downloadFile(
                            token,
                            item.urlData!!
                        ).byteStream(), item.idUnique!!
                    )
                    voices.add(
                        Voice(
                            id = item.idUnique!!,
                            local = false,
                            toRemove = false
                        )
                    )
                }
            }
            CurrentCheckMedia(
                voices = voices,
                pictures = pictures
            )
        }

        data class ImageData(
            @SerializedName("listInfoImage") val list: List<Item>
        ) {

            data class Item(
                @SerializedName("URL") val url: String,
                @SerializedName("SizeType") val sizeType: Int
            )
        }

        private suspend fun saveAudio(inputStream: InputStream, id: String) {
            withContext(Dispatchers.IO) { fileExplorer.inputStreamToVoices(inputStream, id) }
        }
    }
}