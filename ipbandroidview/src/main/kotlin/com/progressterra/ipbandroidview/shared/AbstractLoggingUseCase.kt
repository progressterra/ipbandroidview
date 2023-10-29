package com.progressterra.ipbandroidview.shared

import com.progressterra.ipbandroidview.R
import com.progressterra.ipbandroidview.processes.ToastedException
import com.progressterra.ipbandroidview.processes.utils.MakeToastUseCase
import java.net.SocketTimeoutException
import java.net.UnknownHostException

abstract class AbstractLoggingUseCase(
    private val makeToastUseCase: MakeToastUseCase,
    private val manageResources: ManageResources
) {

    protected suspend fun <T> handle(
        block: suspend () -> T
    ): Result<T> = runCatching {
        block()
    }.onFailure {
        when (it) {
            is UnknownHostException, is SocketTimeoutException -> makeToastUseCase(R.string.no_internet_connection)
            is ToastedException -> makeToastUseCase(it.stringId)
            else -> makeToastUseCase(R.string.unknown_error)
        }
    }
}