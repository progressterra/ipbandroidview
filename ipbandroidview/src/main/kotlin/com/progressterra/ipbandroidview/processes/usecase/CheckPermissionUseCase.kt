package com.progressterra.ipbandroidview.processes.usecase

import com.progressterra.ipbandroidview.shared.ManagePermissionContract
import com.progressterra.ipbandroidview.processes.exception.NoPermissionException

interface CheckPermissionUseCase {

    suspend operator fun invoke(permission: String): Result<Unit>

    class Base(
        private val managePermissionContract: ManagePermissionContract.Client
    ) : CheckPermissionUseCase {

        override suspend fun invoke(permission: String): Result<Unit> = runCatching {
            if (!managePermissionContract.checkPermission(permission))
                throw NoPermissionException()
        }
    }
}