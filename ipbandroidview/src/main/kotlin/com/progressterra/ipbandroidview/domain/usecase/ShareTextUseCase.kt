package com.progressterra.ipbandroidview.domain.usecase

import android.content.Intent
import com.progressterra.ipbandroidview.core.StartActivityContract

interface ShareTextUseCase {

    suspend operator fun invoke(text: String)

    class Base(
        private val startActivityContract: StartActivityContract.Client
    ) : ShareTextUseCase {

        override suspend fun invoke(text: String) {
            val sendIntent = Intent()
            sendIntent.action = Intent.ACTION_SEND
            sendIntent.putExtra(Intent.EXTRA_TEXT, text)
            sendIntent.type = "text/plain"
            val shareIntent = Intent.createChooser(sendIntent, text)
            startActivityContract.start(shareIntent)
        }
    }
}