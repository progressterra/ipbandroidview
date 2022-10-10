package com.progressterra.ipbandroidview.domain

import com.progressterra.ipbandroidapi.api.checklist.ChecklistRepository
import com.progressterra.ipbandroidapi.api.scrm.SCRMRepository
import com.progressterra.ipbandroidview.R
import com.progressterra.ipbandroidview.core.AbstractUseCaseWithToken
import com.progressterra.ipbandroidview.core.ManageResources
import com.progressterra.ipbandroidview.data.ProvideLocation
import com.progressterra.ipbandroidview.ui.organizationaudits.OrganizationAudit

interface OrganizationAuditsUseCase {

    suspend fun organizationsAudits(id: String): List<OrganizationAudit>

    class Base(
        private val repo: ChecklistRepository,
        manageResources: ManageResources,
        provideLocation: ProvideLocation,
        sCRMRepository: SCRMRepository
    ) : OrganizationAuditsUseCase, AbstractUseCaseWithToken(sCRMRepository, provideLocation) {

        private val noData = manageResources.string(R.string.no_data)

        override suspend fun organizationsAudits(
            id: String
        ): List<OrganizationAudit> {
            val availableChecks = withToken {repo.availableChecklistsForPlace(it, id) }
            return buildList {
                availableChecks.map { audits ->
                    audits.map {
                        OrganizationAudit(
                            it.name ?: noData, it.description ?: noData, false
                        )
                    }
                }
            }
        }
    }
}