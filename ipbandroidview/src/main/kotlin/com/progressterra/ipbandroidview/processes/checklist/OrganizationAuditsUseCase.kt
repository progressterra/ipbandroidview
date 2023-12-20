package com.progressterra.ipbandroidview.processes.checklist

import com.progressterra.ipbandroidapi.api.checklist.ChecklistService
import com.progressterra.ipbandroidview.entities.OrganizationAudit
import com.progressterra.ipbandroidview.entities.formatZdt
import com.progressterra.ipbandroidview.entities.parseToZDT
import com.progressterra.ipbandroidview.processes.utils.MakeToastUseCase
import com.progressterra.ipbandroidview.processes.utils.ManageResources
import com.progressterra.ipbandroidview.processes.utils.ObtainAccessToken
import com.progressterra.ipbandroidview.shared.mvi.AbstractTokenUseCase

interface OrganizationAuditsUseCase {

    suspend operator fun invoke(id: String): Result<List<OrganizationAudit>>

    class Base(
        private val checklistService: ChecklistService,
        manageResources: ManageResources,
        obtainAccessToken: ObtainAccessToken,
        makeToastUseCase: MakeToastUseCase
    ) : OrganizationAuditsUseCase,
        AbstractTokenUseCase(obtainAccessToken, makeToastUseCase, manageResources) {

        override suspend fun invoke(id: String): Result<List<OrganizationAudit>> =
            withToken { token ->
                checklistService.availableChecklistsForPlace(token, id).dataList?.map { doc ->
                    OrganizationAudit(
                        id = doc.idUnique!!,
                        name = doc.name ?: "",
                        lastTime = doc.dateUpdated?.parseToZDT()?.formatZdt("dd.MM.yyyy") ?: ""
                    )
                } ?: emptyList()
            }
    }
}