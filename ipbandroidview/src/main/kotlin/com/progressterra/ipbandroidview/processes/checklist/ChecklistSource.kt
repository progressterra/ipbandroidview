package com.progressterra.ipbandroidview.processes.checklist

import com.progressterra.ipbandroidapi.api.checklist.ChecklistService
import com.progressterra.ipbandroidapi.api.checklist.models.FilterAndSort
import com.progressterra.ipbandroidview.entities.Check
import com.progressterra.ipbandroidview.processes.utils.ObtainAccessToken
import com.progressterra.ipbandroidview.shared.mvi.AbstractSource

class ChecklistSource(
    private val obtainAccessToken: ObtainAccessToken,
    private val checklistService: ChecklistService
) : AbstractSource<String, Check>() {

    override val pageSize = 10
    private var idChecklist = ""
    private var currentCategory = ""
    private var categorizedChecks = 0
    private var categoryNumber = 0
    private var prevMaxIndex = 0

    override suspend fun loadPage(skip: Int, take: Int): Result<List<Check>> =
        runCatching {
            val token = obtainAccessToken().getOrThrow()
            if (filter!! != idChecklist) {
                idChecklist = filter!!
                currentCategory = ""
                categorizedChecks = 0
                categoryNumber = 0
                prevMaxIndex = 0
            }
            val checks = checklistService.checklistElements(
                token,
                filter!!,
                FilterAndSort(
                    listFields = emptyList(),
                    sort = null,
                    searchData = "",
                    skip = skip,
                    take = take
                )
            ).dataList!!
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
                ).also {
                    if (checks.lastIndex == index) {
                        prevMaxIndex += index
                    }
                }
            }
        }
}