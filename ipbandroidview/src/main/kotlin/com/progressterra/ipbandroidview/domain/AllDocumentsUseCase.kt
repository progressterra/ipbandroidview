package com.progressterra.ipbandroidview.domain

import com.progressterra.ipbandroidapi.api.checklist.ChecklistRepository
import com.progressterra.ipbandroidapi.api.checklist.model.FilterAndSort
import com.progressterra.ipbandroidapi.api.scrm.SCRMRepository
import com.progressterra.ipbandroidapi.ext.tryOrNull
import com.progressterra.ipbandroidview.R
import com.progressterra.ipbandroidview.core.AbstractUseCaseWithToken
import com.progressterra.ipbandroidview.core.ManageResources
import com.progressterra.ipbandroidview.data.ProvideLocation
import com.progressterra.ipbandroidview.ui.audits.Document

//TODO placeholders

interface AllDocumentsUseCase {

    suspend fun allDocuments(): Result<List<Document>>

    class Base(
        provideLocation: ProvideLocation,
        sCRMRepository: SCRMRepository,
        manageResources: ManageResources,
        private val repo: ChecklistRepository
    ) : AbstractUseCaseWithToken(sCRMRepository, provideLocation), AllDocumentsUseCase {

        private val noData = manageResources.string(R.string.no_data)

        override suspend fun allDocuments(): Result<List<Document>> = handle {
            val documents = withToken {
                repo.allDocuments(
                    it, FilterAndSort(emptyList(), null, "", false, 0, 100)
                )
            }.getOrThrow()
            buildList {
                documents.map { doc ->
                    add(Document(
                        placeId = doc.idrfComPlace!!,
                        checklistId = doc.idUnique!!,
                        name = doc.nameRFCheck ?: noData,
                        done = doc.dateEnd != null,
                        address = doc.nameComPlace ?: noData,
                        percentage = tryOrNull { doc.countDRPositiveAnswer!! / doc.countDR!! }
                            ?: 0,
                        checkCounter = doc.countDR ?: 0,
                        repetitiveness = "PLACEHOLDER", lastTimeChecked = "PLACEHOLDER"))
                }
            }
        }
    }
}