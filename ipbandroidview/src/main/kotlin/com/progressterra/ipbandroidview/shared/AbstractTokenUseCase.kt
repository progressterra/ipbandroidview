package com.progressterra.ipbandroidview.shared

import com.progressterra.ipbandroidview.processes.ObtainAccessToken

abstract class AbstractTokenUseCase(
    private val obtainAccessToken: ObtainAccessToken
) : AbstractLoggingUseCase() {

    protected suspend fun <T> withToken(
        block: suspend (accessToken: String) -> T
    ): Result<T> = handle {
        val result = obtainAccessToken().getOrThrow()
        block(result)
    }
}