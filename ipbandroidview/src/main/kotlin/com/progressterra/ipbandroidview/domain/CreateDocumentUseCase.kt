package com.progressterra.ipbandroidview.domain

import com.progressterra.ipbandroidapi.api.checklist.ChecklistRepository
import com.progressterra.ipbandroidapi.api.checklist.model.DHCheckPerformedEntityCreate
import com.progressterra.ipbandroidapi.api.scrm.SCRMRepository
import com.progressterra.ipbandroidview.core.AbstractUseCaseWithToken
import com.progressterra.ipbandroidview.data.ProvideLocation

interface CreateDocumentUseCase {

    suspend fun createDocument(idChecklist: String, idPlace: String): Result<Unit>

    class Base(
        scrmRepository: SCRMRepository,
        provideLocation: ProvideLocation,
        private val repo: ChecklistRepository
    ) : AbstractUseCaseWithToken(scrmRepository, provideLocation), CreateDocumentUseCase {

        override suspend fun createDocument(
            idChecklist: String,
            idPlace: String
        ): Result<Unit> = handle {
            withToken {
                repo.createDoc(
                    it, DHCheckPerformedEntityCreate(
                        idChecklist, "", idPlace, "", "", ""
                    )
                )
            }.onFailure { throw it }
        }
    }
}