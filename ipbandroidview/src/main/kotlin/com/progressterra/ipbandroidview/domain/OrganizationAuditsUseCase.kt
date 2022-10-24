package com.progressterra.ipbandroidview.domain

import com.progressterra.ipbandroidapi.api.checklist.ChecklistRepository
import com.progressterra.ipbandroidapi.api.scrm.SCRMRepository
import com.progressterra.ipbandroidapi.ext.format
import com.progressterra.ipbandroidapi.ext.parseToDate
import com.progressterra.ipbandroidview.R
import com.progressterra.ipbandroidview.core.AbstractUseCaseWithToken
import com.progressterra.ipbandroidview.core.ManageResources
import com.progressterra.ipbandroidview.data.ProvideLocation
import com.progressterra.ipbandroidview.ui.organizationaudits.OrganizationAudit

interface OrganizationAuditsUseCase {

    suspend fun organizationsAudits(id: String): Result<List<OrganizationAudit>>

    class Base(
        private val repo: ChecklistRepository,
        manageResources: ManageResources,
        provideLocation: ProvideLocation,
        sCRMRepository: SCRMRepository
    ) : OrganizationAuditsUseCase, AbstractUseCaseWithToken(sCRMRepository, provideLocation) {

        private val noData = manageResources.string(R.string.no_data)

        override suspend fun organizationsAudits(
            id: String
        ): Result<List<OrganizationAudit>> = handle {
            val availableChecks =
                withToken { repo.availableChecklistsForPlace(it, id) }.getOrThrow()
            buildList {
                availableChecks?.map { doc ->
                    doc.idUnique?.let {
                        add(
                            OrganizationAudit(
                                id = it,
                                name = doc.name ?: noData,
                                lastTime = doc.dateUpdated?.parseToDate()?.format("dd.MM.yyyy")
                                    ?: noData
                            )
                        )
                    }
                }
            }
        }
    }
}