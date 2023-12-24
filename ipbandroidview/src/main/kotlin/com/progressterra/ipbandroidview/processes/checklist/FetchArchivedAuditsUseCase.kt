package com.progressterra.ipbandroidview.processes.checklist

import com.progressterra.ipbandroidapi.api.checklist.ChecklistService
import com.progressterra.ipbandroidapi.api.checklist.models.FilterAndSort
import com.progressterra.ipbandroidview.entities.ChecklistDocument
import com.progressterra.ipbandroidview.entities.toChecklistDocument
import com.progressterra.ipbandroidview.processes.utils.ObtainAccessToken
import com.progressterra.ipbandroidview.shared.mvi.AbstractSource
import com.progressterra.ipbandroidview.shared.mvi.PagingUseCase

interface FetchArchivedAuditsUseCase : PagingUseCase<Nothing, ChecklistDocument> {

    class Base(
        private val obtainAccessToken: ObtainAccessToken,
        private val checklistService: ChecklistService
    ) : FetchArchivedAuditsUseCase, PagingUseCase.Abstract<Nothing, ChecklistDocument>() {

        override fun createSource() = ArchivedAuditsSource(
            obtainAccessToken = obtainAccessToken,
            checklistService = checklistService
        )
    }
}

class ArchivedAuditsSource(
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
        response.size to response.map { it.toChecklistDocument() }
            .filter { it.finishDate != null }
    }
}