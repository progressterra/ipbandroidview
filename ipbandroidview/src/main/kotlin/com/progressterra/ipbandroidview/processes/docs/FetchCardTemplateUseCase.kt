package com.progressterra.ipbandroidview.processes.docs

import com.progressterra.ipbandroidview.shared.IpbAndroidViewSettings
import com.progressterra.ipbandroidview.entities.Document
import com.progressterra.ipbandroidview.processes.utils.ManageResources
import com.progressterra.ipbandroidview.processes.utils.MakeToastUseCase
import com.progressterra.ipbandroidview.shared.mvi.AbstractLoggingUseCase

interface FetchCardTemplateUseCase {

    suspend operator fun invoke(): Result<Document>

    class Base(
        private val fetchDocTemplateUseCase: FetchDocTemplateUseCase,
        makeToastUseCase: MakeToastUseCase, manageResources: ManageResources
    ) : FetchCardTemplateUseCase, AbstractLoggingUseCase(makeToastUseCase, manageResources) {

        override suspend fun invoke(): Result<Document> = handle {
            fetchDocTemplateUseCase(IpbAndroidViewSettings.BANK_CARDS_TYPE_ID).getOrThrow()
        }
    }
}