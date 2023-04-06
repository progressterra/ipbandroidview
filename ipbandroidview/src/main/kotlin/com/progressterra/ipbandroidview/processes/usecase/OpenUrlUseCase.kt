package com.progressterra.ipbandroidview.processes.usecase

import android.content.Intent
import android.net.Uri
import com.progressterra.ipbandroidview.shared.StartActivityContract

interface OpenUrlUseCase {

    suspend operator fun invoke(url: String)

    class Base(
        private val startActivityContract: StartActivityContract.Client
    ) : OpenUrlUseCase {

        override suspend fun invoke(url: String) {
            val intent = Intent(Intent.ACTION_VIEW)
            intent.data = Uri.parse(url)
            startActivityContract.start(intent)
        }
    }
}