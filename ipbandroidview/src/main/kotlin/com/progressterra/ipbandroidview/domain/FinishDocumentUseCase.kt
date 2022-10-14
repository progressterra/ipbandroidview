package com.progressterra.ipbandroidview.domain

import com.progressterra.ipbandroidapi.api.checklist.ChecklistRepository
import com.progressterra.ipbandroidapi.api.checklist.model.FinalCommentsInput
import com.progressterra.ipbandroidapi.api.scrm.SCRMRepository
import com.progressterra.ipbandroidview.core.AbstractUseCaseWithToken
import com.progressterra.ipbandroidview.data.ProvideLocation

interface FinishDocumentUseCase {

    suspend fun finishDocument(idChecklist: String): Result<Unit>

    class Base(
        scrmRepository: SCRMRepository,
        provideLocation: ProvideLocation,
        private val repo: ChecklistRepository
    ) : AbstractUseCaseWithToken(scrmRepository, provideLocation), FinishDocumentUseCase {

        override suspend fun finishDocument(idChecklist: String): Result<Unit> = handle {
            withToken { repo.finishCheck(it, idChecklist, FinalCommentsInput("")) }
        }
    }
}