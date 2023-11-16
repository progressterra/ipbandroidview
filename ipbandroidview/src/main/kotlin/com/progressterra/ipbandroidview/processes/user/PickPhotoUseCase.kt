package com.progressterra.ipbandroidview.processes.user

import android.net.Uri
import com.progressterra.ipbandroidview.R
import com.progressterra.ipbandroidview.processes.ToastedException
import com.progressterra.ipbandroidview.processes.utils.MakeToastUseCase
import com.progressterra.ipbandroidview.shared.mvi.AbstractLoggingUseCase
import com.progressterra.ipbandroidview.processes.utils.ManageResources
import com.progressterra.ipbandroidview.processes.media.PickPhotoContract

interface PickPhotoUseCase {

    suspend operator fun invoke(): Result<Uri>

    class Base(
        private val pickPhotoContract: PickPhotoContract.Client, makeToastUseCase: MakeToastUseCase,
        manageResources: ManageResources
    ) : PickPhotoUseCase, AbstractLoggingUseCase(makeToastUseCase, manageResources) {

        override suspend fun invoke(): Result<Uri> = handle {
            pickPhotoContract.pickPhoto() ?: throw ToastedException(R.string.choose_photo)
        }
    }
}