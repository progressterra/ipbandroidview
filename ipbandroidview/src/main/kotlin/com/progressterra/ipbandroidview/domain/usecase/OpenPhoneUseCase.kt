package com.progressterra.ipbandroidview.domain.usecase

import android.content.Intent
import android.net.Uri
import com.progressterra.ipbandroidview.core.StartActivityContract

interface OpenPhoneUseCase {

    suspend operator fun invoke(phone: String)

    class Base(
        private val startActivityContract: StartActivityContract.Client
    ) : OpenPhoneUseCase {

        override suspend fun invoke(phone: String) {
            val intent = Intent(Intent.ACTION_DIAL)
            intent.data = Uri.parse("tel:$phone")
            startActivityContract.start(intent)
        }
    }
}