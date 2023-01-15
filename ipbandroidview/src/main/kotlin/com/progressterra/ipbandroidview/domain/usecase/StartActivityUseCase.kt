package com.progressterra.ipbandroidview.domain.usecase

import android.content.Intent
import com.progressterra.ipbandroidview.core.StartActivityContract

interface StartActivityUseCase {

    suspend operator fun invoke(intent: Intent)

    class Base(
        private val startActivityContract: StartActivityContract.Client
    ) : StartActivityUseCase {

        override suspend fun invoke(intent: Intent) {
            startActivityContract.start(intent)
        }
    }
}