package com.progressterra.ipbandroidview.processes.checklist

import com.progressterra.ipbandroidapi.api.checklist.ChecklistService
import com.progressterra.ipbandroidapi.api.checklist.models.FilterAndSort
import com.progressterra.ipbandroidview.entities.ChecklistDocument
import com.progressterra.ipbandroidview.entities.toChecklistDocument
import com.progressterra.ipbandroidview.processes.utils.ObtainAccessToken
import com.progressterra.ipbandroidview.shared.mvi.AbstractSource

class OngoingAuditsSource(
    private val checklistService: ChecklistService,
    private val obtainAccessToken: ObtainAccessToken
) : AbstractSource<Nothing, ChecklistDocument>() {

    override val pageSize = 6
    override suspend fun loadPage(
        skip: Int,
        take: Int
    ): Result<Pair<Int, List<ChecklistDocument>>> = runCatching {
        val token = obtainAccessToken().getOrThrow()
        val response = checklistService.allDocuments(
            token, FilterAndSort(
                listFields = emptyList(),
                sort = null,
                searchData = "",
                skip = skip,
                take = take
            )
        ).dataList!!
        response.size to response.map { it.toChecklistDocument() }.filter { it.finishDate == null }
    }
}