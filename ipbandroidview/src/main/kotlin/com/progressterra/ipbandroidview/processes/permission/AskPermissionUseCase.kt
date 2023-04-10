package com.progressterra.ipbandroidview.processes.permission

import com.progressterra.ipbandroidview.shared.activity.ManagePermissionContract

interface AskPermissionUseCase {

    suspend operator fun invoke(permission: String): Result<Unit>

    class Base(
        private val managePermissionContract: ManagePermissionContract.Client
    ) : AskPermissionUseCase {

        override suspend fun invoke(permission: String): Result<Unit> = runCatching {
            managePermissionContract.requestPermission(permission)
        }
    }
}