package com.progressterra.ipbandroidview.domain

import com.progressterra.ipbandroidapi.api.checklist.ChecklistRepository
import com.progressterra.ipbandroidapi.api.scrm.SCRMRepository
import com.progressterra.ipbandroidview.R
import com.progressterra.ipbandroidview.composable.yesno.YesNo
import com.progressterra.ipbandroidview.core.AbstractUseCaseWithToken
import com.progressterra.ipbandroidview.core.ManageResources
import com.progressterra.ipbandroidview.data.ProvideLocation
import com.progressterra.ipbandroidview.ui.checklist.Check

interface DocumentChecklistUseCase {

    suspend fun documentChecklist(id: String): Result<List<Check>>

    class Base(
        provideLocation: ProvideLocation,
        scrmRepository: SCRMRepository,
        manageResources: ManageResources,
        private val repo: ChecklistRepository,
    ) : DocumentChecklistUseCase,
        AbstractUseCaseWithToken(scrmRepository, provideLocation) {

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

                            add(
                                Check(
                                    id = id,
                                    category = "${check.parameter?.indexName ?: noData}. ${check.parameter?.internalName ?: noData}",
                                    name = check.shortDescription ?: noData,
                                    yesNo = yesNo,
                                    comment = check.answerCheckList?.comments ?: "",
                                    description = check.description ?: noData
                                )
                            )
                        }
                    }
                }
            )
        }
    }
}