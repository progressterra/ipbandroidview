package com.progressterra.ipbandroidview.domain

import com.progressterra.ipbandroidapi.api.ipbmediadata.IPBMediaDataRepository
import com.progressterra.ipbandroidapi.api.scrm.SCRMRepository
import com.progressterra.ipbandroidview.core.AbstractUseCaseWithSaving
import com.progressterra.ipbandroidview.core.FileExplorer
import com.progressterra.ipbandroidview.core.Photo
import com.progressterra.ipbandroidview.data.ProvideLocation
import com.progressterra.ipbandroidview.ui.checklist.Check
import com.progressterra.ipbandroidview.ui.checklist.CurrentCheckDetails

interface CheckMediaDetailsUseCase {

    suspend fun checkDetails(check: Check): Result<CurrentCheckDetails>

    class Base(
        provideLocation: ProvideLocation,
        scrmRepository: SCRMRepository,
        fileExplorer: FileExplorer,
        private val mediaDataRepository: IPBMediaDataRepository
    ) : AbstractUseCaseWithSaving(scrmRepository, provideLocation, fileExplorer),
        CheckMediaDetailsUseCase {

        override suspend fun checkDetails(check: Check): Result<CurrentCheckDetails> = handle {
            val attachedPhotos = withToken {
                mediaDataRepository.attachedToEntity(
                    it,
                    check.id
                )
            }.getOrThrow()?.filter { it.contentType == 0 }?.map { item ->
                savePicture(withToken {
                    mediaDataRepository.downloadFile(
                        it,
                        item.urlData!!
                    )
                }.getOrThrow(), item.idUnique!!)
                item.idUnique!!
            }?.map { Photo(it, false) }
            val attachedVoiceMessageData = withToken {
                mediaDataRepository.attachedToEntity(
                    it,
                    check.id
                )
            }.getOrThrow()?.firstOrNull { it.contentType == 6 }
            attachedVoiceMessageData?.let { data ->
                saveAudio(withToken {
                    mediaDataRepository.downloadFile(
                        it,
                        data.urlData!!
                    )
                }.getOrThrow(), data.idUnique!!)
            }
            CurrentCheckDetails(
                attachedVoicePointer = attachedVoiceMessageData?.idUnique,
                attachedPhotosPointers = attachedPhotos ?: emptyList()
            )
        }
    }
}