package com.progressterra.ipbandroidview.pages.wantthis

import com.progressterra.ipbandroidview.IpbAndroidViewSettings
import com.progressterra.ipbandroidview.processes.docs.FetchDocTemplateUseCase
import com.progressterra.ipbandroidview.shared.AbstractLoggingUseCase

interface FetchWantThisUseCase {

    suspend operator fun invoke(): Result<WantThisScreenState>

    class Base(
        private val fetchDocTemplateUseCase: FetchDocTemplateUseCase
    ) : FetchWantThisUseCase, AbstractLoggingUseCase() {

        override suspend fun invoke(): Result<WantThisScreenState> = handle {
            fetchDocTemplateUseCase(IpbAndroidViewSettings.WANT_THIS_DOC_TYPE_ID).getOrThrow()
                .toWantThisScreenState()
        }
    }
}
