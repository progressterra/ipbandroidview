package com.progressterra.ipbandroidview.processes.user

import android.net.Uri
import com.progressterra.ipbandroidview.processes.utils.MakeToastUseCase
import com.progressterra.ipbandroidview.shared.AbstractLoggingUseCase
import com.progressterra.ipbandroidview.shared.ManageResources
import com.progressterra.ipbandroidview.shared.activity.PickPhotoContract

interface PickPhotoUseCase {

    suspend operator fun invoke(): Result<Uri>

    class Base(
        private val pickPhotoContract: PickPhotoContract.Client, makeToastUseCase: MakeToastUseCase,
        manageResources: ManageResources
    ) : PickPhotoUseCase, AbstractLoggingUseCase(makeToastUseCase, manageResources) {

        override suspend fun invoke(): Result<Uri> = handle {
            pickPhotoContract.pickPhoto() ?: throw Exception("Photo was not picked")
        }
    }
}