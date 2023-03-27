package com.progressterra.ipbandroidview.processes.usecase

import com.progressterra.ipbandroidview.core.ManagePermissionContract

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