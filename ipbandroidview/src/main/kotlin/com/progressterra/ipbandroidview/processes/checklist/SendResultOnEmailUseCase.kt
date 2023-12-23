package com.progressterra.ipbandroidview.processes.checklist

import com.progressterra.ipbandroidapi.api.checklist.ChecklistService
import com.progressterra.ipbandroidapi.api.checklist.models.StatusResult
import com.progressterra.ipbandroidview.R
import com.progressterra.ipbandroidview.processes.ToastedException
import com.progressterra.ipbandroidview.processes.utils.MakeToastUseCase
import com.progressterra.ipbandroidview.processes.utils.ManageResources
import com.progressterra.ipbandroidview.processes.utils.ObtainAccessToken
import com.progressterra.ipbandroidview.shared.UserData
import com.progressterra.ipbandroidview.shared.mvi.AbstractTokenUseCase


interface SendResultOnEmailUseCase {

    suspend operator fun invoke(docId: String): Result<Unit>

    class Base(
        obtainAccessToken: ObtainAccessToken,
        manageResources: ManageResources,
        private val makeToastUseCase: MakeToastUseCase,
        private val checklistService: ChecklistService
    ) : AbstractTokenUseCase(obtainAccessToken, makeToastUseCase, manageResources),
        SendResultOnEmailUseCase {

        override suspend fun invoke(
            docId: String,
        ): Result<Unit> = withToken { token ->
            if (UserData.email.isEmpty()) {
                throw ToastedException(R.string.error_email_not_found)
            }
            if (checklistService.sendOnEmail(token, docId).result?.status != StatusResult.SUCCESS) {
                throw ToastedException(R.string.error_send_on_email)
            } else {
                makeToastUseCase(R.string.success_send_on_email)
            }
        }
    }
}