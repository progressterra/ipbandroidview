package com.progressterra.ipbandroidview.domain.fetchexisting

import com.progressterra.ipbandroidapi.api.checklist.ChecklistRepository
import com.progressterra.ipbandroidapi.api.scrm.SCRMRepository
import com.progressterra.ipbandroidview.core.AbstractUseCaseWithToken
import com.progressterra.ipbandroidview.data.ProvideLocation

interface FetchExistingAuditUseCase {

    suspend fun fetchExistingAudit(idPlace: String, idChecklist: String): Result<String>

    class Base(
        scrmRepository: SCRMRepository,
        provideLocation: ProvideLocation,
        private val repo: ChecklistRepository
    ) : AbstractUseCaseWithToken(scrmRepository, provideLocation), FetchExistingAuditUseCase {

        override suspend fun fetchExistingAudit(
            idPlace: String,
            idChecklist: String
        ): Result<String> = runCatching {
            withToken { repo.activeDoc(it, idPlace, idChecklist) }.getOrThrow()?.idUnique!!
        }
    }
}