package com.progressterra.ipbandroidview.domain

import com.progressterra.ipbandroidapi.api.checklist.ChecklistRepository
import com.progressterra.ipbandroidapi.api.checklist.model.FilterAndSort
import com.progressterra.ipbandroidapi.api.scrm.SCRMRepository
import com.progressterra.ipbandroidapi.ext.parseToDate
import com.progressterra.ipbandroidview.R
import com.progressterra.ipbandroidview.composable.yesno.YesNo
import com.progressterra.ipbandroidview.core.AbstractUseCaseWithToken
import com.progressterra.ipbandroidview.core.ManageResources
import com.progressterra.ipbandroidview.data.ProvideLocation
import com.progressterra.ipbandroidview.ui.checklist.Check

interface ChecklistUseCase {

    suspend fun details(id: String): Result<List<Check>>

    class Base(
        provideLocation: ProvideLocation,
        manageResources: ManageResources,
        scrmRepository: SCRMRepository,
        private val checklistRepository: ChecklistRepository
    ) : AbstractUseCaseWithToken(scrmRepository, provideLocation), ChecklistUseCase {

        private val noData = manageResources.string(R.string.no_data)

        override suspend fun details(id: String): Result<List<Check>> = runCatching {
            val result = withToken {
                checklistRepository.checklistElements(
                    it, id, FilterAndSort(emptyList(), null, "", false, 0, 100)
                )
            }.getOrThrow()
            buildList {
                result?.map { check ->
                    add(
                        CheckDTO(
                            id = check.idUnique!!,
                            category = check.parameter?.internalName ?: noData,
                            name = check.shortDescription ?: noData,
                            yesNo = YesNo.NONE,
                            comment = "",
                            description = check.description ?: noData,
                            categoryNumber = check.parameter?.indexName?.toInt()!!,
                            dateAdded = check.dateAdded?.parseToDate()!!
                        )
                    )
                }
            }.sortedBy { it.dateAdded }.mapIndexed { index, checkDTO ->
                Check(
                    id = checkDTO.id,
                    name = checkDTO.name,
                    description = checkDTO.description,
                    category = checkDTO.category,
                    categoryNumber = checkDTO.categoryNumber,
                    ordinal = index,
                    yesNo = checkDTO.yesNo,
                    comment = checkDTO.comment
                )
            }
        }
    }
}