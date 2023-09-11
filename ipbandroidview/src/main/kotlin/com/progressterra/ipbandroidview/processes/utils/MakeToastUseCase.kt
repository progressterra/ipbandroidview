package com.progressterra.ipbandroidview.processes.utils

import androidx.annotation.StringRes
import com.progressterra.ipbandroidview.shared.AbstractLoggingUseCase
import com.progressterra.ipbandroidview.shared.activity.MakeToastContract

interface MakeToastUseCase {

    suspend operator fun invoke(@StringRes resId: Int): Result<Unit>

    class Base(
        private val contract: MakeToastContract.Client
    ) : MakeToastUseCase, AbstractLoggingUseCase() {

        override suspend fun invoke(resId: Int) = handle {
            contract.makeToast(resId)
        }
    }
}