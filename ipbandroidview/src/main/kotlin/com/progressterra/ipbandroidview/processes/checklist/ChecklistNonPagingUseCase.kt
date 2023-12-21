package com.progressterra.ipbandroidview.processes.checklist

import com.progressterra.ipbandroidapi.api.checklist.ChecklistService
import com.progressterra.ipbandroidapi.api.checklist.models.FilterAndSort
import com.progressterra.ipbandroidview.entities.Check
import com.progressterra.ipbandroidview.processes.utils.MakeToastUseCase
import com.progressterra.ipbandroidview.processes.utils.ManageResources
import com.progressterra.ipbandroidview.processes.utils.ObtainAccessToken
import com.progressterra.ipbandroidview.shared.mvi.AbstractTokenUseCase

interface ChecklistNonPagingUseCase {

    suspend operator fun invoke(id: String): Result<List<Check>>

    class Base(
        private val checklistService: ChecklistService,
        obtainAccessToken: ObtainAccessToken,
        makeToastUseCase: MakeToastUseCase,
        manageResources: ManageResources
    ) : ChecklistNonPagingUseCase,
        AbstractTokenUseCase(obtainAccessToken, makeToastUseCase, manageResources) {

        override suspend fun invoke(id: String): Result<List<Check>> = withToken { token ->
            val checks = checklistService.checklistElements(
                token,
                id,
                FilterAndSort(
                    listFields = emptyList(),
                    sort = null,
                    searchData = "",
                    skip = 0,
                    take = 300
                )
            ).dataList!!
            var currentCategory = ""
            var categorizedChecks = 0
            var categoryNumber = 0
            checks.mapIndexed { index, check ->
                if (check.parameter?.internalName != currentCategory) {
                    currentCategory = check.parameter?.internalName!!
                    categoryNumber++
                    categorizedChecks = index
                }
                Check(
                    id = check.idUnique!!,
                    category = currentCategory,
                    name = check.shortDescription ?: "",
                    yesNo = null,
                    comment = "",
                    description = check.description ?: "",
                    categoryNumber = categoryNumber,
                    ordinal = index + 1 - categorizedChecks
                )
            }
        }
    }

}