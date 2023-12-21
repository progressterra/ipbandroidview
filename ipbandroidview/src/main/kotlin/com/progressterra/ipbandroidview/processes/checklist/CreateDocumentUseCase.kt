package com.progressterra.ipbandroidview.processes.checklist

import com.progressterra.ipbandroidapi.api.checklist.ChecklistService
import com.progressterra.ipbandroidapi.api.checklist.models.DHCheckPerformedEntityCreate
import com.progressterra.ipbandroidapi.api.checklist.models.StatusResult
import com.progressterra.ipbandroidapi.format
import com.progressterra.ipbandroidview.R
import com.progressterra.ipbandroidview.processes.ToastedException
import com.progressterra.ipbandroidview.processes.utils.MakeToastUseCase
import com.progressterra.ipbandroidview.processes.utils.ManageResources
import com.progressterra.ipbandroidview.processes.utils.ObtainAccessToken
import com.progressterra.ipbandroidview.shared.mvi.AbstractTokenUseCase
import java.util.Date

interface CreateDocumentUseCase {

    suspend operator fun invoke(idChecklist: String, idPlace: String): Result<String>

    class Base(
        private val checklistService: ChecklistService,
        makeToastUseCase: MakeToastUseCase,
        manageResources: ManageResources,
        obtainAccessToken: ObtainAccessToken
    ) : AbstractTokenUseCase(obtainAccessToken, makeToastUseCase, manageResources),
        CreateDocumentUseCase {

        override suspend fun invoke(
            idChecklist: String,
            idPlace: String
        ): Result<String> = withToken { token ->
            val result = checklistService.createDoc(
                token, DHCheckPerformedEntityCreate(
                    idChecklist,
                    "00000000-0000-0000-0000-000000000000",
                    idPlace,
                    Date(System.currentTimeMillis()).format(),
                    "",
                    ""
                )
            )
            if (result.result?.status != StatusResult.SUCCESS) {
                throw ToastedException(R.string.failure)
            }
            result.data?.idUnique!!
        }
    }
}