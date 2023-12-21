package com.progressterra.ipbandroidview.processes.checklist

import com.progressterra.ipbandroidapi.api.checklist.ChecklistService
import com.progressterra.ipbandroidapi.api.checklist.models.FilterAndSort
import com.progressterra.ipbandroidapi.parseToDate
import com.progressterra.ipbandroidview.entities.ChecklistDocument
import com.progressterra.ipbandroidview.entities.ChecklistStats
import com.progressterra.ipbandroidview.entities.formatZdt
import com.progressterra.ipbandroidview.entities.parseToZDT
import com.progressterra.ipbandroidview.processes.utils.ObtainAccessToken
import com.progressterra.ipbandroidview.shared.mvi.AbstractSource
import com.progressterra.ipbandroidview.shared.tryOrNull

class AllDocumentsSource(
    private val checklistService: ChecklistService,
    private val obtainAccessToken: ObtainAccessToken
) : AbstractSource<Nothing, ChecklistDocument>() {

    override val pageSize = 10
    override suspend fun loadPage(skip: Int, take: Int): Result<List<ChecklistDocument>> =
        runCatching {
            val token = obtainAccessToken().getOrThrow()
            checklistService.allDocuments(
                token,
                FilterAndSort(
                    listFields = emptyList(),
                    sort = null,
                    searchData = "",
                    skip = skip,
                    take = take
                )
            ).dataList?.map { doc ->
                ChecklistDocument(
                    placeId = doc.idrfComPlace!!,
                    checklistId = doc.idrfCheck!!,
                    documentId = doc.idUnique!!,
                    name = doc.nameRFCheck ?: "",
                    address = doc.nameComPlace ?: "",
                    checkCounter = doc.countDR ?: 0,
                    finishDate = doc.dateEnd?.parseToZDT()?.formatZdt("dd.MM"),
                    stats = ChecklistStats(
                        total = doc.countDR ?: 0,
                        successful = doc.countDRPositiveAnswer ?: 0,
                        failed = doc.countDRNegativeAnswer ?: 0,
                        remaining = tryOrNull { doc.countDR!! - doc.countDRPositiveAnswer!! - doc.countDRNegativeAnswer!! }
                            ?: 0
                    ),
                    isRecentlyFinished = if (doc.dateEnd == null)
                        false
                    else
                        System.currentTimeMillis() - doc.dateEnd!!.parseToDate()!!.time <= 24 * 60 * 60 * 1000
                )
            } ?: emptyList()
        }
}