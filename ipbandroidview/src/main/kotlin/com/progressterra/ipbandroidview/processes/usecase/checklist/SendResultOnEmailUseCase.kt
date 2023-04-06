package com.progressterra.ipbandroidview.processes.usecase.checklist

import com.progressterra.ipbandroidapi.api.checklist.ChecklistRepository
import com.progressterra.ipbandroidapi.api.scrm.SCRMRepository
import com.progressterra.ipbandroidview.shared.AbstractUseCase
import com.progressterra.ipbandroidview.shared.ProvideLocation
import com.progressterra.ipbandroidview.processes.exception.NoEmailException
import com.progressterra.ipbandroidview.processes.usecase.user.FetchUserEmailUseCase
import com.progressterra.ipbandroidview.ext.throwOnFailure

interface SendResultOnEmailUseCase {

    suspend operator fun invoke(docId: String): Result<Unit>

    class Base(
        scrmRepository: SCRMRepository,
        provideLocation: ProvideLocation,
        private val checklistRepository: ChecklistRepository,
        private val fetchUserEmailUseCase: FetchUserEmailUseCase
    ) : AbstractUseCase(scrmRepository, provideLocation), SendResultOnEmailUseCase {

        override suspend fun invoke(
            docId: String,
        ): Result<Unit> = withToken { token ->
            val email = fetchUserEmailUseCase()
            if (email.isBlank())
                throw NoEmailException()
            checklistRepository.sendOnEmail(token, docId, email).throwOnFailure()
        }
    }
}