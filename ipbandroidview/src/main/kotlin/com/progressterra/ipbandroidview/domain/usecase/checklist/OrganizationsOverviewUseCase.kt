package com.progressterra.ipbandroidview.domain.usecase.checklist

import com.progressterra.ipbandroidapi.api.checklist.ChecklistRepository
import com.progressterra.ipbandroidapi.api.checklist.model.FilterAndSort
import com.progressterra.ipbandroidapi.api.checklist.model.SortData
import com.progressterra.ipbandroidapi.api.checklist.model.TypeVariantSort
import com.progressterra.ipbandroidapi.api.scrm.SCRMRepository
import com.progressterra.ipbandroidapi.ext.format
import com.progressterra.ipbandroidapi.ext.parseToDate
import com.progressterra.ipbandroidapi.ext.tryOrNull
import com.progressterra.ipbandroidview.R
import com.progressterra.ipbandroidview.composable.OrganizationOverview
import com.progressterra.ipbandroidview.core.AbstractUseCase
import com.progressterra.ipbandroidview.core.ManageResources
import com.progressterra.ipbandroidview.core.ProvideLocation
import com.progressterra.ipbandroidview.model.ChecklistStats
import com.progressterra.ipbandroidview.model.Document
import com.progressterra.ipbandroidview.model.Organization

interface OrganizationsOverviewUseCase {

    suspend operator fun invoke(): Result<List<OrganizationOverview>>

    class Base(
        provideLocation: ProvideLocation,
        sCRMRepository: SCRMRepository,
        manageResources: ManageResources,
        private val repo: ChecklistRepository
    ) : AbstractUseCase(sCRMRepository, provideLocation), OrganizationsOverviewUseCase {

        private val noData = manageResources.string(R.string.no_data)

        override suspend fun invoke(): Result<List<OrganizationOverview>> = withToken { token ->
            val places = repo.availableChecklistsAndDocs(token).getOrThrow()
            buildList {
                places?.map { place ->
                    add(
                        Organization(
                            address = place.address ?: noData,
                            id = place.idUnique!!,
                            name = place.name ?: noData,
                            imageUrl = place.imageURL ?: "",
                            audits = place.countAvailableRFCheck?.toString() ?: noData,
                            documents = place.countDHCheckPerformedForExecution?.toString()
                                ?: noData,
                            latitude = place.latitude ?: 0.0,
                            longitude = place.longitude ?: 0.0
                        )
                    )
                }
            }
            val documents = repo.allDocuments(
                token, FilterAndSort(
                    emptyList(), SortData("dateEnd", TypeVariantSort.DESC), "", false, 0, 300
                )
            ).getOrThrow()
            val allDocs = buildList {
                documents?.map { doc ->
                    add(Document(placeId = doc.idrfComPlace!!,
                        checklistId = doc.idrfCheck!!,
                        documentId = doc.idUnique!!,
                        name = doc.nameRFCheck ?: noData,
                        address = doc.nameComPlace ?: noData,
                        checkCounter = doc.countDR ?: 0,
                        finishDate = doc.dateEnd?.parseToDate()?.format("dd.MM"),
                        stats = ChecklistStats(total = doc.countDR ?: 0,
                            successful = doc.countDRPositiveAnswer ?: 0,
                            failed = doc.countDRNegativeAnswer ?: 0,
                            remaining = tryOrNull { doc.countDR!! - doc.countDRPositiveAnswer!! - doc.countDRNegativeAnswer!! }
                                ?: 0)))
                }
            }
            val archived = allDocs.filter { it.finishDate != null }
            buildList {
                places?.map { place ->
                    add(
                        OrganizationOverview(
                            name = place.name ?: noData,
                            ongoing = allDocs.filter { it.placeId == place.idUnique && it.finishDate == null },
                            completed = archived.filter { it.placeId == place.idUnique && it.finishDate != null },
                            archived = archived
                        )
                    )
                }
            }
        }
    }
}