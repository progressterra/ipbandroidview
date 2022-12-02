package com.progressterra.ipbandroidview.domain.usecase.checklist

import com.google.gson.Gson
import com.google.gson.annotations.SerializedName
import com.progressterra.ipbandroidapi.api.ipbmediadata.IPBMediaDataRepository
import com.progressterra.ipbandroidapi.api.scrm.SCRMRepository
import com.progressterra.ipbandroidview.core.AbstractUseCaseSaving
import com.progressterra.ipbandroidview.core.FileExplorer
import com.progressterra.ipbandroidview.core.ProvideLocation
import com.progressterra.ipbandroidview.model.Check
import com.progressterra.ipbandroidview.model.CheckPicture
import com.progressterra.ipbandroidview.model.Voice
import com.progressterra.ipbandroidview.ui.checklist.CurrentCheckMedia

interface CheckMediaDetailsUseCase {

    suspend operator fun invoke(check: Check): Result<CurrentCheckMedia>

    class Base(
        provideLocation: ProvideLocation,
        scrmRepository: SCRMRepository,
        fileExplorer: FileExplorer,
        private val mediaDataRepository: IPBMediaDataRepository,
        private val gson: Gson
    ) : AbstractUseCaseSaving(scrmRepository, provideLocation, fileExplorer),
        CheckMediaDetailsUseCase {

        override suspend fun invoke(
            check: Check
        ): Result<CurrentCheckMedia> = withToken { token ->
            val voices = mutableListOf<Voice>()
            val pictures = mutableListOf<CheckPicture>()
            mediaDataRepository.attachedToEntity(
                token,
                check.id
            ).getOrThrow()?.forEach { item ->
                if (item.contentType == 0) {
                    val sizes = gson.fromJson(item.dataJSON, ImageData::class.java).list
                    pictures.add(
                        CheckPicture(
                            id = item.idUnique!!,
                            local = false,
                            toRemove = false,
                            thumbnail = sizes.first { it.sizeType == 1 }.url,
                            fullSize = sizes.first { it.sizeType == 3 }.url
                        )
                    )
                } else if (item.contentType == 6 && voices.size == 0) {
                    saveAudio(
                        mediaDataRepository.downloadFile(
                            token,
                            item.urlData!!
                        ).getOrThrow(), item.idUnique!!
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
    }
}