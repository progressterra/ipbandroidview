package com.progressterra.ipbandroidview.processes.permission

import com.progressterra.ipbandroidview.R
import com.progressterra.ipbandroidview.processes.ToastedException
import com.progressterra.ipbandroidview.processes.utils.MakeToastUseCase
import com.progressterra.ipbandroidview.processes.utils.ManagePermissionContract
import com.progressterra.ipbandroidview.processes.utils.ManageResources
import com.progressterra.ipbandroidview.shared.mvi.AbstractLoggingUseCase

interface CheckPermissionUseCase {

    suspend operator fun invoke(permission: String): Result<Unit>

    class Base(
        private val managePermissionContract: ManagePermissionContract.Client,
        manageResources: ManageResources,
        makeToastUseCase: MakeToastUseCase
    ) : CheckPermissionUseCase, AbstractLoggingUseCase(makeToastUseCase, manageResources) {

        override suspend fun invoke(permission: String): Result<Unit> = runCatching {
            if (!managePermissionContract.checkPermission(permission))
                throw ToastedException(R.string.no_permission)
        }
    }
}