package com.progressterra.ipbandroidview.processes.checklist

import com.progressterra.ipbandroidapi.api.checklist.ChecklistService
import com.progressterra.ipbandroidapi.api.checklist.models.FinalCommentsInput
import com.progressterra.ipbandroidapi.api.checklist.models.StatusResult
import com.progressterra.ipbandroidview.R
import com.progressterra.ipbandroidview.processes.ToastedException
import com.progressterra.ipbandroidview.processes.utils.MakeToastUseCase
import com.progressterra.ipbandroidview.processes.utils.ManageResources
import com.progressterra.ipbandroidview.processes.utils.ObtainAccessToken
import com.progressterra.ipbandroidview.shared.mvi.AbstractTokenUseCase

interface FinishDocumentUseCase {

    suspend operator fun invoke(idChecklist: String): Result<Unit>

    class Base(
        private val checklistService: ChecklistService,
        makeToastUseCase: MakeToastUseCase,
        obtainAccessToken: ObtainAccessToken,
        manageResources: ManageResources
    ) : AbstractTokenUseCase(obtainAccessToken, makeToastUseCase, manageResources),
        FinishDocumentUseCase {

        override suspend fun invoke(
            idChecklist: String
        ): Result<Unit> = withToken { token ->
            if (checklistService.finishCheck(
                    token, idChecklist, FinalCommentsInput("")
                ).result?.status != StatusResult.SUCCESS
            ) {
                throw ToastedException(R.string.order_id)
            }
        }
    }
}