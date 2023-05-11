package com.progressterra.ipbandroidview.domain.usecase.store

import android.graphics.Bitmap
import com.progressterra.ipbandroidapi.api.scrm.SCRMRepository
import com.progressterra.ipbandroidview.core.AbstractUseCase
import com.progressterra.ipbandroidview.core.ProvideLocation
import com.progressterra.ipbandroidview.domain.usecase.qr.CreateQr

interface CreateQrUseCase {

    suspend operator fun invoke(): Result<Bitmap>

    class Base(
        provideLocation: ProvideLocation,
        scrmRepository: SCRMRepository,
        private val createQr: CreateQr
    ) : CreateQrUseCase,
        AbstractUseCase(scrmRepository, provideLocation) {

        override suspend fun invoke(): Result<Bitmap> = withToken { token ->
            createQr(token)
        }
    }
}