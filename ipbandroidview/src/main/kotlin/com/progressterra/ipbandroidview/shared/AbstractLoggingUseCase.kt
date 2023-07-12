package com.progressterra.ipbandroidview.shared

import android.util.Log
import com.progressterra.ipbandroidview.IpbAndroidViewSettings

abstract class AbstractLoggingUseCase {

    protected fun <T> log(message: T) {
        if (IpbAndroidViewSettings.DEBUG) {
            Log.d(javaClass::getSimpleName.toString(), message.toString())
        }
    }

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