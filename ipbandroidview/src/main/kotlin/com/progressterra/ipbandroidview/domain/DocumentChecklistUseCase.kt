package com.progressterra.ipbandroidview.domain

import com.progressterra.ipbandroidapi.api.checklist.ChecklistRepository
import com.progressterra.ipbandroidapi.api.scrm.SCRMRepository
import com.progressterra.ipbandroidview.R
import com.progressterra.ipbandroidview.components.yesno.YesNo
import com.progressterra.ipbandroidview.core.AbstractUseCase
import com.progressterra.ipbandroidview.core.ManageResources
import com.progressterra.ipbandroidview.core.ProvideLocation
import com.progressterra.ipbandroidview.ui.checklist.Check

interface DocumentChecklistUseCase {

    suspend fun documentChecklist(id: String): Result<List<Check>>

    class Base(
        provideLocation: ProvideLocation,
        scrmRepository: SCRMRepository,
        manageResources: ManageResources,
        private val repo: ChecklistRepository,
    ) : DocumentChecklistUseCase, AbstractUseCase(scrmRepository, provideLocation) {

        private val noData = manageResources.string(R.string.no_data)

        override suspend fun documentChecklist(
            id: String
        ): Result<List<Check>> = runCatching {
            val responseChecklist = withToken { repo.checklistForDoc(it, id) }.getOrThrow()
            var currentCategory = ""
            var categorizedChecks = 0
            var categoryNumber = 0
            buildList {
                responseChecklist?.mapIndexed { index, check ->
                    val yesNo =
                        if (check.answerCheckList?.yesNo == true) YesNo.YES else if (check.answerCheckList?.yesNo == false) YesNo.NO else YesNo.NONE
                    if (check.parameter?.internalName != currentCategory) {
                        currentCategory = check.parameter?.internalName!!
                        categoryNumber++
                        categorizedChecks = index
                    }
                    add(
                        Check(
                            id = check.idUnique!!,
                            name = check.shortDescription ?: noData,
                            description = check.description ?: noData,
                            category = currentCategory,
                            categoryNumber = categoryNumber,
                            ordinal = index + 1 - categorizedChecks,
                            yesNo = yesNo,
                            comment = check.answerCheckList?.comments ?: ""
                        )
                    )
                }
            }
        }
    }
}