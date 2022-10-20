package com.progressterra.ipbandroidview.domain

import com.progressterra.ipbandroidapi.api.ipbmediadata.IPBMediaDataRepository
import com.progressterra.ipbandroidapi.api.scrm.SCRMRepository
import com.progressterra.ipbandroidview.core.AbstractUseCasePictureSaving
import com.progressterra.ipbandroidview.core.FileExplorer
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
    ) : AbstractUseCasePictureSaving(scrmRepository, provideLocation, fileExplorer),
        CheckMediaDetailsUseCase {

        override suspend fun checkDetails(check: Check): Result<CurrentCheckDetails> = handle {
            val attachedPhotos = withToken {
                mediaDataRepository.attachedToEntity(
                    it,
                    check.id
                )
            }.getOrThrow()?.filter { it.contentType == 0 }?.map { item ->
                save(withToken {
                    mediaDataRepository.downloadFile(
                        it,
                        item.urlData!!
                    )
                }.getOrThrow(), item.idUnique!!)
                item.idUnique!!
            }
            val attachedVoiceMessageData = withToken {
                mediaDataRepository.attachedToEntity(
                    it,
                    check.id
                )
            }.getOrThrow()?.firstOrNull { it.contentType == 6 }
            attachedVoiceMessageData?.let { data ->
                save(withToken {
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