package com.progressterra.ipbandroidview.domain.usecase.checklist

import com.progressterra.ipbandroidapi.api.checklist.ChecklistRepository
import com.progressterra.ipbandroidapi.api.scrm.SCRMRepository
import com.progressterra.ipbandroidview.core.AbstractUseCase
import com.progressterra.ipbandroidview.core.ProvideLocation
import com.progressterra.ipbandroidview.ext.throwOnFailure

interface SendResultOnEmailUseCase {

    suspend operator fun invoke(docId: String, email: String): Result<Unit>

    class Base(
        scrmRepository: SCRMRepository,
        provideLocation: ProvideLocation,
        private val checklistRepository: ChecklistRepository
    ) : AbstractUseCase(scrmRepository, provideLocation), SendResultOnEmailUseCase {

        override suspend fun invoke(
            docId: String,
            email: String
        ): Result<Unit> = withToken { token ->
            checklistRepository.sendOnEmail(token, docId, email.trim()).throwOnFailure()
        }
    }
}