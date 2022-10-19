package com.progressterra.ipbandroidview.domain

import com.progressterra.ipbandroidapi.api.checklist.ChecklistRepository
import com.progressterra.ipbandroidapi.api.ipbmediadata.IPBMediaDataRepository
import com.progressterra.ipbandroidapi.api.scrm.SCRMRepository
import com.progressterra.ipbandroidview.R
import com.progressterra.ipbandroidview.composable.yesno.YesNo
import com.progressterra.ipbandroidview.core.AbstractUseCaseWithTokenAndSaving
import com.progressterra.ipbandroidview.core.ManageResources
import com.progressterra.ipbandroidview.core.FileExplorer
import com.progressterra.ipbandroidview.data.ProvideLocation
import com.progressterra.ipbandroidview.ui.checklist.Check

interface DocumentChecklistUseCase {

    suspend fun documentChecklist(id: String): Result<List<Check>>

    class Base(
        provideLocation: ProvideLocation,
        scrmRepository: SCRMRepository,
        manageResources: ManageResources,
        fileExplorer: FileExplorer,
        private val repo: ChecklistRepository,
        private val mediaDataRepository: IPBMediaDataRepository,
    ) : DocumentChecklistUseCase,
        AbstractUseCaseWithTokenAndSaving(scrmRepository, provideLocation, fileExplorer) {

        private val noData = manageResources.string(R.string.no_data)

        override suspend fun documentChecklist(
            id: String
        ): Result<List<Check>> {
            val responseChecklist = withToken { repo.checklistForDoc(it, id) }.getOrThrow()
            return Result.success(
                buildList {
                    responseChecklist?.map { check ->
                        check.idUnique?.let { id ->
                            val yesNo =
                                if (check.answerCheckList?.yesNo == true) YesNo.YES else if (check.answerCheckList?.yesNo == false) YesNo.NO else YesNo.NONE
                            val attachedPhotos = withToken {
                                mediaDataRepository.attachedToEntity(
                                    it,
                                    check.idUnique!!
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
                                    check.idUnique!!
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
                            add(
                                Check(
                                    id = id,
                                    category = "${check.parameter?.indexName ?: noData}. ${check.parameter?.internalName ?: noData}",
                                    name = check.shortDescription ?: noData,
                                    yesNo = yesNo,
                                    comment = check.answerCheckList?.comments ?: "",
                                    description = check.description ?: noData,
                                    attachedVoicePointer = attachedVoiceMessageData?.idUnique,
                                    attachedPhotosPointers = attachedPhotos
                                )
                            )
                        }
                    }
                }
            )
        }
    }
}
