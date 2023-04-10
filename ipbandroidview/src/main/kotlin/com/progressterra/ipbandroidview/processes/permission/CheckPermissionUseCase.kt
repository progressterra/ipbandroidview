package com.progressterra.ipbandroidview.processes.permission

import com.progressterra.ipbandroidview.shared.activity.ManagePermissionContract

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