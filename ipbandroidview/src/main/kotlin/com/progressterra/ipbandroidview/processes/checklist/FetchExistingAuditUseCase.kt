package com.progressterra.ipbandroidview.processes.checklist

import com.progressterra.ipbandroidapi.api.checklist.ChecklistService
import com.progressterra.ipbandroidview.processes.utils.MakeToastUseCase
import com.progressterra.ipbandroidview.processes.utils.ManageResources
import com.progressterra.ipbandroidview.processes.utils.ObtainAccessToken
import com.progressterra.ipbandroidview.shared.mvi.AbstractTokenUseCase

interface FetchExistingAuditUseCase {

    suspend operator fun invoke(idPlace: String, idChecklist: String): Result<String>

    class Base(
        private val checklistService: ChecklistService,
        makeToastUseCase: MakeToastUseCase,
        manageResources: ManageResources,
        obtainAccessToken: ObtainAccessToken
    ) : AbstractTokenUseCase(obtainAccessToken, makeToastUseCase, manageResources),
        FetchExistingAuditUseCase {

        override suspend fun invoke(
            idPlace: String,
            idChecklist: String
        ): Result<String> = withToken { token ->
            checklistService.activeDoc(token, idPlace, idChecklist).data?.idUnique!!
        }
    }
}