package com.progressterra.ipbandroidview.domain

import com.progressterra.ipbandroidapi.api.checklist.ChecklistRepository
import com.progressterra.ipbandroidview.R
import com.progressterra.ipbandroidview.base.ManageResources
import com.progressterra.ipbandroidview.ui.organizationaudits.OrganizationAudit
import java.util.*

//TODO

interface OrganizationAuditsUseCase {

    suspend fun organizationsAudits(id: String): List<OrganizationAudit>

    class Base(
        private val repo: ChecklistRepository, manageResources: ManageResources
    ) : OrganizationAuditsUseCase {

        private val noData = manageResources.string(R.string.no_data)

        override suspend fun organizationsAudits(
            id: String
        ): List<OrganizationAudit> = buildList {
            repo.availableChecklistsForPlace(UUID.fromString(id)).map { audits ->
                audits?.map {
                    OrganizationAudit(
                        it.name ?: noData, it.description ?: noData, false
                    )
                }
            }
        }
    }
}