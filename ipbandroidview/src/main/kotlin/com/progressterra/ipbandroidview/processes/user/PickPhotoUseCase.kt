package com.progressterra.ipbandroidview.processes.user

import com.progressterra.ipbandroidview.shared.AbstractLoggingUseCase
import com.progressterra.ipbandroidview.shared.activity.PickPhotoContract

interface PickPhotoUseCase {

    suspend operator fun invoke(): Result<String>

    class Base(
        private val pickPhotoContract: PickPhotoContract.Client
    ) : PickPhotoUseCase, AbstractLoggingUseCase() {

        override suspend fun invoke(): Result<String> = handle {
            pickPhotoContract.pickPhoto()
        }
    }
}