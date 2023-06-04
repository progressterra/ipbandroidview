package com.progressterra.ipbandroidview.shared

import android.util.Log
import com.progressterra.ipbandroidview.IpbAndroidViewSettings

abstract class AbstractLoggingUseCase {

    protected suspend fun <T> handle(
        block: suspend () -> T
    ): Result<T> = runCatching {
        block()
    }.onFailure {
        if (IpbAndroidViewSettings.DEBUG) Log.e(
            javaClass::getSimpleName.toString(), it.message, it
        )
    }
}