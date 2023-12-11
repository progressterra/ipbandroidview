package com.progressterra.ipbandroidview.shared.mvi

import com.progressterra.ipbandroidview.shared.IpbAndroidViewSettings
import com.progressterra.ipbandroidview.R
import com.progressterra.ipbandroidview.processes.SilentException
import com.progressterra.ipbandroidview.processes.ToastedException
import com.progressterra.ipbandroidview.processes.utils.MakeToastUseCase
import com.progressterra.ipbandroidview.processes.utils.ManageResources
import java.net.ConnectException
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
            is UnknownHostException, is SocketTimeoutException, is ConnectException ->
                makeToastUseCase(R.string.no_internet_connection)

            is ToastedException -> if (it.stringId == 0) makeToastUseCase(it.customMsg!!) else makeToastUseCase(
                it.stringId
            )

            is SilentException -> Unit

            else -> {
                makeToastUseCase(R.string.unknown_error)
                if (IpbAndroidViewSettings.DEBUG) it.printStackTrace()
            }
        }
    }
}