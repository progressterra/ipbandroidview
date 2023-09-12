package com.progressterra.ipbandroidview.processes.utils

import android.content.Context
import android.widget.Toast
import androidx.annotation.StringRes
import com.progressterra.ipbandroidview.shared.AbstractLoggingUseCase

interface MakeToastUseCase {

    suspend operator fun invoke(@StringRes resId: Int): Result<Unit>

    class Base(
        private val context: Context
    ) : MakeToastUseCase, AbstractLoggingUseCase() {

        override suspend fun invoke(resId: Int) = handle {
            Toast.makeText(context, resId, Toast.LENGTH_LONG).show()
        }
    }
}