package com.progressterra.ipbandroidview.processes.usecase.checklist

import com.progressterra.ipbandroidapi.api.checklist.ChecklistRepository
import com.progressterra.ipbandroidapi.api.checklist.model.FinalCommentsInput
import com.progressterra.ipbandroidapi.api.scrm.SCRMRepository
import com.progressterra.ipbandroidview.shared.AbstractUseCase
import com.progressterra.ipbandroidview.shared.ProvideLocation
import com.progressterra.ipbandroidview.ext.throwOnFailure

interface FinishDocumentUseCase {

    suspend operator fun invoke(idChecklist: String): Result<Unit>

    class Base(
        scrmRepository: SCRMRepository,
        provideLocation: ProvideLocation,
        private val repo: ChecklistRepository
    ) : AbstractUseCase(scrmRepository, provideLocation), FinishDocumentUseCase {

        override suspend fun invoke(
            idChecklist: String
        ): Result<Unit> = withToken { token ->
            repo.finishCheck(
                token, idChecklist, FinalCommentsInput("")
            ).throwOnFailure()
        }
    }
}