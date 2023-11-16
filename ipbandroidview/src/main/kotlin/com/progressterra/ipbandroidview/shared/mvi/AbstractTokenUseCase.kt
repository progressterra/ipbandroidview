package com.progressterra.ipbandroidview.shared.mvi

import com.progressterra.ipbandroidview.processes.utils.ObtainAccessToken
import com.progressterra.ipbandroidview.processes.utils.MakeToastUseCase
import com.progressterra.ipbandroidview.processes.utils.ManageResources

abstract class AbstractTokenUseCase(
    private val obtainAccessToken: ObtainAccessToken,
    makeToastUseCase: MakeToastUseCase,
    manageResources: ManageResources
) : AbstractLoggingUseCase(makeToastUseCase, manageResources) {

    protected suspend fun <T> withToken(
        block: suspend (accessToken: String) -> T
    ): Result<T> = handle {
        val result = obtainAccessToken().getOrThrow()
        block(result)
    }
}
