package com.progressterra.ipbandroidview.processes.checklist

import com.progressterra.ipbandroidapi.api.checklist.ChecklistService
import com.progressterra.ipbandroidview.entities.ChecklistDocument
import com.progressterra.ipbandroidview.processes.utils.ObtainAccessToken
import com.progressterra.ipbandroidview.shared.mvi.PagingUseCase

interface AllDocumentsUseCase : PagingUseCase<Nothing, ChecklistDocument> {

    class Base(
        private val obtainAccessToken: ObtainAccessToken,
        private val checklistService: ChecklistService,
    ) : PagingUseCase.Abstract<Nothing, ChecklistDocument>(), AllDocumentsUseCase {

        override fun createSource() = AllDocumentsSource(checklistService, obtainAccessToken)
    }
}