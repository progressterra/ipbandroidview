package com.progressterra.ipbandroidview.processes.permission

import com.progressterra.ipbandroidview.processes.utils.ManagePermissionContract

interface AskPermissionUseCase {

    suspend operator fun invoke(permission: String)

    class Base(
        private val managePermissionContract: ManagePermissionContract.Client
    ) : AskPermissionUseCase {

        override suspend fun invoke(permission: String) {
            managePermissionContract.requestPermission(permission)
        }
    }
}