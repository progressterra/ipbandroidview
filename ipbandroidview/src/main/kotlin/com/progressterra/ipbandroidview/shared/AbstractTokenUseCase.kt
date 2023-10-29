package com.progressterra.ipbandroidview.shared

import com.progressterra.ipbandroidview.processes.ObtainAccessToken
import com.progressterra.ipbandroidview.processes.utils.MakeToastUseCase

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
