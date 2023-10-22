package com.progressterra.ipbandroidview.processes

import com.progressterra.ipbandroidview.IpbAndroidViewSettings
import com.progressterra.ipbandroidview.entities.Document
import com.progressterra.ipbandroidview.processes.docs.FetchDocTemplateUseCase
import com.progressterra.ipbandroidview.shared.AbstractLoggingUseCase

interface FetchCardTemplateUseCase {

    suspend operator fun invoke(): Result<Document>

    class Base(
        private val fetchDocTemplateUseCase: FetchDocTemplateUseCase
    ) : FetchCardTemplateUseCase, AbstractLoggingUseCase() {

        override suspend fun invoke(): Result<Document> = handle {
            fetchDocTemplateUseCase(IpbAndroidViewSettings.BANK_CARDS_TYPE_ID).getOrThrow()
        }
    }
}