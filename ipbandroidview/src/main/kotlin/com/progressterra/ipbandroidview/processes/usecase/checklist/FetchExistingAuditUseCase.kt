package com.progressterra.ipbandroidview.processes.usecase.checklist

import com.progressterra.ipbandroidapi.api.checklist.ChecklistRepository
import com.progressterra.ipbandroidapi.api.scrm.SCRMRepository
import com.progressterra.ipbandroidview.core.AbstractUseCase
import com.progressterra.ipbandroidview.core.ProvideLocation

interface FetchExistingAuditUseCase {

    suspend operator fun invoke(idPlace: String, idChecklist: String): Result<String>

    class Base(
        scrmRepository: SCRMRepository,
        provideLocation: ProvideLocation,
        private val repo: ChecklistRepository
    ) : AbstractUseCase(scrmRepository, provideLocation), FetchExistingAuditUseCase {

        override suspend fun invoke(
            idPlace: String,
            idChecklist: String
        ): Result<String> = withToken { token ->
            repo.activeDoc(token, idPlace, idChecklist).getOrThrow()?.idUnique!!
        }
    }
}