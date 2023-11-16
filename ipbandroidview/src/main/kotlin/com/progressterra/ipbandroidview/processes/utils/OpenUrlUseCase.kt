package com.progressterra.ipbandroidview.processes.utils

import android.content.Intent
import android.net.Uri

interface OpenUrlUseCase {

    suspend operator fun invoke(url: String)

    class Base(
        private val startActivityContract: StartActivityContract.Client
    ) : OpenUrlUseCase {

        override suspend fun invoke(url: String) {
            val intent = Intent(Intent.ACTION_VIEW, Uri.parse(url))
            startActivityContract.start(intent)
        }
    }
}