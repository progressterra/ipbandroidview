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

interface AllDocumentsUseCase {

    suspend fun allDocuments(): Result<List<Document>>

    class Base(
        provideLocation: ProvideLocation,
        sCRMRepository: SCRMRepository,
        manageResources: ManageResources,
        private val repo: ChecklistRepository
    ) : AbstractUseCaseWithToken(sCRMRepository, provideLocation), AllDocumentsUseCase {

        private val noData = manageResources.string(R.string.no_data)

        override suspend fun allDocuments(): Result<List<Document>> {
            val documents = withToken {
                repo.allDocuments(
                    it, FilterAndSort(null, null, null, null, null, null)
                )
            }
            if (documents.isFailure) return Result.failure(documents.exceptionOrNull()!!)
            return Result.success(buildList {
                documents.map { docs ->
                    docs.map { doc ->
                        doc.idUnique?.let { docId ->
                            add(Document(id = docId,
                                name = doc.nameRFCheck ?: noData,
                                done = doc.dateEnd != null,
                                address = doc.nameComPlace ?: noData,
                                percentage = tryOrNull { doc.countDRPositiveAnswer!! + doc.countDRNegativeAnswer!! / doc.countDR!! }
                                    ?: 0))
                        }
                    }
                }
            })
        }
    }
}