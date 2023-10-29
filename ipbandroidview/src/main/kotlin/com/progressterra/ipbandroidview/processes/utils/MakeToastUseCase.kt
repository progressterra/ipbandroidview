package com.progressterra.ipbandroidview.processes.utils

import android.content.Context
import android.widget.Toast
import androidx.annotation.StringRes

interface MakeToastUseCase {

    suspend operator fun invoke(@StringRes resId: Int)

    class Base(
        private val context: Context
    ) : MakeToastUseCase {

        override suspend fun invoke(resId: Int)  {
            Toast.makeText(context, resId, Toast.LENGTH_LONG).show()
        }
    }
}