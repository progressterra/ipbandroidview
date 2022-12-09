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
import com.progressterra.ipbandroidview.core.AbstractUseCase
import com.progressterra.ipbandroidview.core.ManageResources
import com.progressterra.ipbandroidview.core.ProvideLocation
import com.progressterra.ipbandroidview.model.checklist.ChecklistStats
import com.progressterra.ipbandroidview.model.checklist.Document

interface AllDocumentsUseCase {

    suspend operator fun invoke(): Result<List<Document>>

    class Base(
        provideLocation: ProvideLocation,
        sCRMRepository: SCRMRepository,
        manageResources: ManageResources,
        private val repo: ChecklistRepository
    ) : AbstractUseCase(sCRMRepository, provideLocation), AllDocumentsUseCase {

        private val noData = manageResources.string(R.string.no_data)

        override suspend fun invoke(): Result<List<Document>> = withToken { token ->
            val documents = repo.allDocuments(
                token,
                FilterAndSort(
                    emptyList(),
                    SortData("dateEnd", TypeVariantSort.DESC),
                    "",
                    false,
                    0,
                    300
                )
            ).getOrThrow()
            buildList {
                documents?.map { doc ->
                    add(
                        Document(
                            placeId = doc.idrfComPlace!!,
                            checklistId = doc.idrfCheck!!,
                            documentId = doc.idUnique!!,
                            name = doc.nameRFCheck ?: noData,
                            address = doc.nameComPlace ?: noData,
                            checkCounter = doc.countDR ?: 0,
                            finishDate = doc.dateEnd?.parseToDate()?.format("dd.MM"),
                            stats = ChecklistStats(
                                total = doc.countDR ?: 0,
                                successful = doc.countDRPositiveAnswer ?: 0,
                                failed = doc.countDRNegativeAnswer ?: 0,
                                remaining = tryOrNull { doc.countDR!! - doc.countDRPositiveAnswer!! - doc.countDRNegativeAnswer!! }
                                    ?: 0
                            )
                        )
                    )
                }
            }
        }
    }
}