package com.progressterra.ipbandroidview.pages.bankcarddetails

import com.progressterra.ipbandroidview.IpbAndroidViewSettings
import com.progressterra.ipbandroidview.processes.docs.FetchDocTemplateUseCase
import com.progressterra.ipbandroidview.shared.AbstractLoggingUseCase

interface FetchCardTemplateUseCase {

    suspend operator fun invoke(): Result<BankCardDetailsScreenState>

    class Base(
        private val fetchDocTemplateUseCase: FetchDocTemplateUseCase
    ) : FetchCardTemplateUseCase, AbstractLoggingUseCase() {

        override suspend fun invoke(): Result<BankCardDetailsScreenState> = handle {
            fetchDocTemplateUseCase(IpbAndroidViewSettings.BANK_CARDS_TYPE_ID).getOrThrow()
                .toBankCardState().toBankCardDetailsScreenState().copy(isNew = true)
        }
    }
}