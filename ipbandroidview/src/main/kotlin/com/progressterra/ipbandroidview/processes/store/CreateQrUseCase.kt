package com.progressterra.ipbandroidview.processes.store

import android.graphics.Bitmap
import com.progressterra.ipbandroidapi.api.scrm.SCRMRepository
import com.progressterra.ipbandroidview.shared.AbstractUseCase
import com.progressterra.ipbandroidview.processes.location.ProvideLocation
import com.progressterra.ipbandroidview.processes.usecase.qr.CreateQr

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