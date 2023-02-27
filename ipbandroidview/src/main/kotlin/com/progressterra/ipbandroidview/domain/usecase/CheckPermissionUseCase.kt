package com.progressterra.ipbandroidview.domain.usecase

import android.util.Log
import com.progressterra.ipbandroidview.core.ManagePermissionContract
import com.progressterra.ipbandroidview.domain.exception.NoPermissionException

interface CheckPermissionUseCase {

    suspend operator fun invoke(permission: String): Result<Unit>

    class Base(
        private val managePermissionContract: ManagePermissionContract.Client
    ) : CheckPermissionUseCase {

        override suspend fun invoke(permission: String): Result<Unit> = runCatching {
            Log.d("camera", "invoke permission: $permission")
            if (!managePermissionContract.checkPermission(permission))
                throw NoPermissionException()
        }
    }
}