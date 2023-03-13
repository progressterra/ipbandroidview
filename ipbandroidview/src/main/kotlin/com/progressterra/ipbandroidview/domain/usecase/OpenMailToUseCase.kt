package com.progressterra.ipbandroidview.domain.usecase

import android.content.Intent
import android.net.Uri
import com.progressterra.ipbandroidview.core.StartActivityContract

interface OpenMailToUseCase {

    suspend operator fun invoke(address: String)

    class Base(
        private val startActivityContract: StartActivityContract.Client
    ) : OpenMailToUseCase {

        override suspend fun invoke(address: String) {
            val intent = Intent(Intent.ACTION_SENDTO)
            intent.data = Uri.parse("mailto:")
            intent.putExtra(Intent.EXTRA_EMAIL, arrayOf(address))
            intent.putExtra(Intent.EXTRA_SUBJECT, "")
            intent.putExtra(Intent.EXTRA_TEXT, "")
            startActivityContract.start(intent)
        }
    }
}