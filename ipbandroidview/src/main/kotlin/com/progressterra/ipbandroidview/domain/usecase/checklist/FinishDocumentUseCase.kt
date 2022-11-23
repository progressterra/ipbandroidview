package com.progressterra.ipbandroidview.domain.usecase.checklist

import com.progressterra.ipbandroidapi.api.checklist.ChecklistRepository
import com.progressterra.ipbandroidapi.api.checklist.model.FinalCommentsInput
import com.progressterra.ipbandroidapi.api.scrm.SCRMRepository
import com.progressterra.ipbandroidview.core.AbstractUseCase
import com.progressterra.ipbandroidview.core.ProvideLocation

interface FinishDocumentUseCase {

    suspend fun finishDocument(idChecklist: String): Result<Unit>

    class Base(
        scrmRepository: SCRMRepository,
        provideLocation: ProvideLocation,
        private val repo: ChecklistRepository
    ) : AbstractUseCase(scrmRepository, provideLocation), FinishDocumentUseCase {

        override suspend fun finishDocument(
            idChecklist: String
        ): Result<Unit> = withToken { token ->
            repo.finishCheck(
                token,
                idChecklist,
                FinalCommentsInput("")
            ).onFailure { throw it }
        }
    }
}