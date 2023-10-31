package com.progressterra.ipbandroidview.processes.utils

import android.content.Context
import android.widget.Toast
import androidx.annotation.StringRes
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

interface MakeToastUseCase {

    suspend operator fun invoke(@StringRes resId: Int)

    suspend operator fun invoke(text: String)

    class Base(
        private val context: Context
    ) : MakeToastUseCase {

        override suspend fun invoke(text: String) {
            withContext(Dispatchers.Main) {
                Toast.makeText(context, text, Toast.LENGTH_LONG).show()
            }
        }

        override suspend fun invoke(resId: Int) {
            withContext(Dispatchers.Main) {
                Toast.makeText(context, resId, Toast.LENGTH_LONG).show()
            }
        }
    }
}