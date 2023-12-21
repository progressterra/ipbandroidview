package com.progressterra.ipbandroidview.processes.checklist

import com.progressterra.ipbandroidapi.api.checklist.ChecklistService
import com.progressterra.ipbandroidview.entities.Check
import com.progressterra.ipbandroidview.processes.utils.MakeToastUseCase
import com.progressterra.ipbandroidview.processes.utils.ManageResources
import com.progressterra.ipbandroidview.processes.utils.ObtainAccessToken
import com.progressterra.ipbandroidview.shared.mvi.AbstractTokenUseCase

interface DocumentChecklistUseCase {

    suspend operator fun invoke(id: String): Result<List<Check>>

    class Base(
        private val checklistService: ChecklistService,
        obtainAccessToken: ObtainAccessToken,
        manageResources: ManageResources,
        makeToastUseCase: MakeToastUseCase
    ) : DocumentChecklistUseCase,
        AbstractTokenUseCase(obtainAccessToken, makeToastUseCase, manageResources) {

        override suspend fun invoke(
            id: String
        ): Result<List<Check>> = withToken { token ->
            val responseChecklist = checklistService.checklistForDoc(token, id).dataList!!
            var currentCategory = ""
            var categorizedChecks = 0
            var categoryNumber = 0
            responseChecklist.mapIndexed { index, check ->
                if (check.parameter?.internalName != currentCategory) {
                    currentCategory = check.parameter?.internalName!!
                    categoryNumber++
                    categorizedChecks = index
                }
                Check(
                    id = check.idUnique!!,
                    name = check.shortDescription ?: "",
                    description = check.description ?: "",
                    category = currentCategory,
                    categoryNumber = categoryNumber,
                    ordinal = index + 1 - categorizedChecks,
                    yesNo = check.answerCheckList?.yesNo,
                    comment = check.answerCheckList?.comments ?: ""
                )
            }
        }
    }
}