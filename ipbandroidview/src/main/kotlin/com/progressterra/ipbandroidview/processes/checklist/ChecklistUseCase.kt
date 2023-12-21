package com.progressterra.ipbandroidview.processes.checklist

import com.progressterra.ipbandroidapi.api.checklist.ChecklistService
import com.progressterra.ipbandroidview.entities.Check
import com.progressterra.ipbandroidview.processes.utils.ObtainAccessToken
import com.progressterra.ipbandroidview.shared.mvi.PagingUseCase

interface ChecklistUseCase : PagingUseCase<String, Check> {

    class Base(
        private val obtainAccessToken: ObtainAccessToken,
        private val checklistService: ChecklistService
    ) : PagingUseCase.Abstract<String, Check>(), ChecklistUseCase {

        override fun createSource() = ChecklistSource(obtainAccessToken, checklistService)
    }
}

