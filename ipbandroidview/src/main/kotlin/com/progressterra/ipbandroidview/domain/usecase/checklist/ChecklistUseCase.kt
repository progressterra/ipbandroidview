package com.progressterra.ipbandroidview.domain.usecase.checklist

import com.progressterra.ipbandroidapi.api.checklist.ChecklistRepository
import com.progressterra.ipbandroidapi.api.checklist.model.FilterAndSort
import com.progressterra.ipbandroidapi.api.scrm.SCRMRepository
import com.progressterra.ipbandroidview.R
import com.progressterra.ipbandroidview.core.AbstractUseCase
import com.progressterra.ipbandroidview.core.ManageResources
import com.progressterra.ipbandroidview.core.ProvideLocation
import com.progressterra.ipbandroidview.model.checklist.Check

interface ChecklistUseCase {

    suspend operator fun invoke(id: String): Result<List<Check>>

    class Base(
        provideLocation: ProvideLocation,
        manageResources: ManageResources,
        scrmRepository: SCRMRepository,
        private val checklistRepository: ChecklistRepository
    ) : AbstractUseCase(scrmRepository, provideLocation), ChecklistUseCase {

        private val noData = manageResources.string(R.string.no_data)

        override suspend fun invoke(id: String): Result<List<Check>> = withToken { token ->
            val result = checklistRepository.checklistElements(
                token, id, FilterAndSort(emptyList(), null, "", false, 0, 300)
            ).getOrThrow()
            var currentCategory = ""
            var categorizedChecks = 0
            var categoryNumber = 0
            buildList {
                result?.mapIndexed { index, check ->
                    if (check.parameter?.internalName != currentCategory) {
                        currentCategory = check.parameter?.internalName!!
                        categoryNumber++
                        categorizedChecks = index
                    }
                    add(
                        Check(
                            id = check.idUnique!!,
                            category = currentCategory,
                            name = check.shortDescription ?: noData,
                            yesNo = null,
                            comment = "",
                            description = check.description ?: noData,
                            categoryNumber = categoryNumber,
                            ordinal = index + 1 - categorizedChecks
                        )
                    )
                }
            }
        }
    }
}