package com.progressterra.ipbandroidview.processes.checklist

import com.progressterra.ipbandroidapi.api.checklist.ChecklistService
import com.progressterra.ipbandroidapi.api.checklist.models.FilterAndSort
import com.progressterra.ipbandroidview.entities.ChecklistDocument
import com.progressterra.ipbandroidview.entities.ChecklistStats
import com.progressterra.ipbandroidview.entities.Organization
import com.progressterra.ipbandroidview.entities.OrganizationOverview
import com.progressterra.ipbandroidview.entities.formatZdt
import com.progressterra.ipbandroidview.entities.parseToZDT
import com.progressterra.ipbandroidview.processes.utils.MakeToastUseCase
import com.progressterra.ipbandroidview.processes.utils.ManageResources
import com.progressterra.ipbandroidview.processes.utils.ObtainAccessToken
import com.progressterra.ipbandroidview.shared.mvi.AbstractTokenUseCase
import com.progressterra.ipbandroidview.shared.tryOrNull


interface OrganizationsOverviewUseCase {

    suspend operator fun invoke(): Result<List<OrganizationOverview>>

    class Base(
        private val checklistService: ChecklistService,
        obtainAccessToken: ObtainAccessToken,
        makeToastUseCase: MakeToastUseCase,
        manageResources: ManageResources
    ) : AbstractTokenUseCase(obtainAccessToken, makeToastUseCase, manageResources),
        OrganizationsOverviewUseCase {

        override suspend fun invoke(): Result<List<OrganizationOverview>> = withToken { token ->
            val places = checklistService.availableChecklistsAndDocs(token).dataList!!
            places.map { place ->
                Organization(
                    address = place.address ?: "",
                    id = place.idUnique!!,
                    name = place.name ?: "",
                    imageUrl = place.listImages?.firstOrNull()?.urlData ?: "",
                    audits = place.countAvailableRFCheck?.toString() ?: "",
                    documents = place.countDHCheckPerformedForExecution?.toString()
                        ?: "",
                    latitude = place.latitude ?: 0.0,
                    longitude = place.longitude ?: 0.0
                )
            }
            val documents = checklistService.allDocuments(
                token, FilterAndSort(
                    listFields = emptyList(),
                    sort = null,
                    searchData = "",
                    skip = 0,
                    take = 300
                )
            ).dataList!!
            val allDocs =
                documents.map { doc ->
                    ChecklistDocument(placeId = doc.idrfComPlace!!,
                        checklistId = doc.idrfCheck!!,
                        documentId = doc.idUnique!!,
                        name = doc.nameRFCheck ?: "",
                        address = doc.nameComPlace ?: "",
                        checkCounter = doc.countDR ?: 0,
                        finishDate = doc.dateEnd?.parseToZDT()?.formatZdt("dd.MM"),
                        stats = ChecklistStats(total = doc.countDR ?: 0,
                            successful = doc.countDRPositiveAnswer ?: 0,
                            failed = doc.countDRNegativeAnswer ?: 0,
                            remaining = tryOrNull { doc.countDR!! - doc.countDRPositiveAnswer!! - doc.countDRNegativeAnswer!! }
                                ?: 0), isRecentlyFinished = false)
                }

            val archived = allDocs.filter { it.finishDate != null }

            places.map { place ->
                OrganizationOverview(
                    name = place.name ?: "",
                    ongoing = allDocs.filter { it.placeId == place.idUnique && it.finishDate == null },
                    completed = archived.filter { it.placeId == place.idUnique && it.finishDate != null },
                    archived = archived
                )

            }

        }
    }
}