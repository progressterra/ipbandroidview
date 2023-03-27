package com.progressterra.ipbandroidview.processes.usecase.checklist

import com.progressterra.ipbandroidapi.api.checklist.ChecklistRepository
import com.progressterra.ipbandroidapi.api.scrm.SCRMRepository
import com.progressterra.ipbandroidview.R
import com.progressterra.ipbandroidview.core.AbstractUseCase
import com.progressterra.ipbandroidview.core.ManageResources
import com.progressterra.ipbandroidview.core.ProvideLocation
import com.progressterra.ipbandroidview.model.Check

interface DocumentChecklistUseCase {

    suspend operator fun invoke(id: String): Result<List<Check>>

    class Base(
        provideLocation: ProvideLocation,
        scrmRepository: SCRMRepository,
        manageResources: ManageResources,
        private val repo: ChecklistRepository,
    ) : DocumentChecklistUseCase, AbstractUseCase(scrmRepository, provideLocation) {

        private val noData = manageResources.string(R.string.no_data)

        override suspend fun invoke(
            id: String
        ): Result<List<Check>> = withToken { token ->
            val responseChecklist = repo.checklistForDoc(token, id).getOrThrow()
            var currentCategory = ""
            var categorizedChecks = 0
            var categoryNumber = 0
            buildList {
                responseChecklist?.mapIndexed { index, check ->
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
                            yesNo = check.answerCheckList?.yesNo,
                            comment = check.answerCheckList?.comments ?: ""
                        )
                    )
                }
            }
        }
    }
}