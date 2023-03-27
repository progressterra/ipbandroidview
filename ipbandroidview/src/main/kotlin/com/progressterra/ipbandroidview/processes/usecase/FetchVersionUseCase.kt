package com.progressterra.ipbandroidview.processes.usecase

import android.content.Context

interface FetchVersionUseCase {

    suspend operator fun invoke(): String

    class Base(
        private val context: Context,
    ) : FetchVersionUseCase {

        override suspend fun invoke(): String {
            val packageInfo = context.packageManager.getPackageInfo(context.packageName, 0)
            return packageInfo.versionName
        }
    }
}