package com.progressterra.ipbandroidview.domain

import com.google.gson.Gson
import com.google.gson.annotations.SerializedName
import com.progressterra.ipbandroidapi.api.ipbmediadata.IPBMediaDataRepository
import com.progressterra.ipbandroidapi.api.scrm.SCRMRepository
import com.progressterra.ipbandroidview.core.AbstractUseCaseAudioSaving
import com.progressterra.ipbandroidview.core.FileExplorer
import com.progressterra.ipbandroidview.core.Picture
import com.progressterra.ipbandroidview.core.voice.Voice
import com.progressterra.ipbandroidview.data.ProvideLocation
import com.progressterra.ipbandroidview.ui.checklist.Check
import com.progressterra.ipbandroidview.ui.checklist.CurrentCheckMedia

interface CheckMediaDetailsUseCase {

    suspend fun checkDetails(check: Check): Result<CurrentCheckMedia>

    class Base(
        provideLocation: ProvideLocation,
        scrmRepository: SCRMRepository,
        fileExplorer: FileExplorer,
        private val mediaDataRepository: IPBMediaDataRepository,
        private val gson: Gson
    ) : AbstractUseCaseAudioSaving(scrmRepository, provideLocation, fileExplorer),
        CheckMediaDetailsUseCase {

        override suspend fun checkDetails(check: Check): Result<CurrentCheckMedia> = handle {
            val voices = mutableListOf<Voice>()
            val pictures = mutableListOf<Picture>()
            withToken {
                mediaDataRepository.attachedToEntity(
                    it,
                    check.id
                )
            }.getOrThrow()?.forEach { item ->
                if (item.contentType == 0) {
                    val sizes = gson.fromJson(item.dataJSON, ImageData::class.java).list
                    Picture.Remote(
                        toRemove = false,
                        thumbnail = sizes.first { it.sizeType == 1 }.url,
                        fullSize = sizes.first { it.sizeType == 0 }.url
                    )
                } else if (item.contentType == 6 && voices.size == 0) {
                    saveAudio(withToken {
                        mediaDataRepository.downloadFile(
                            it,
                            item.urlData!!
                        )
                    }.getOrThrow(), item.idUnique!!)
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