package com.progressterra.ipbandroidview.processes.utils

import com.progressterra.ipbandroidview.shared.AbstractLoggingUseCase
import com.progressterra.ipbandroidview.shared.activity.PickDateContract

interface PickDateUseCase {

    suspend operator fun invoke(): Result<String>

    class Base(
        private val contract: PickDateContract.Client
    ) : PickDateUseCase, AbstractLoggingUseCase() {
        override suspend operator fun invoke(): Result<String> = handle {
            contract.pickDate()
        }
    }
}