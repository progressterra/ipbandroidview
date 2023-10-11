package com.progressterra.ipbandroidview.shared

import com.progressterra.ipbandroidview.processes.ObtainAccessToken
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.receiveAsFlow

abstract class AbstractCacheTokenUseCase<T>(
    obtainAccessToken: ObtainAccessToken
) : AbstractTokenUseCase(obtainAccessToken), CacheUseCase<T> {

    private val storage: MutableList<T> = mutableListOf()

    private val _resultChannel = Channel<Result<T>>()
    override val resultFlow = _resultChannel.receiveAsFlow()

    protected suspend fun withCache(
        block: suspend (accessToken: String) -> T
    ) {
        if (storage.isNotEmpty()) {
            _resultChannel.send(Result.success(storage.last()))
        }
        val newResult = withToken(block)
        if (newResult.isSuccess) {
            val newValue = newResult.getOrThrow()
            if (storage.isEmpty()) {
                storage.add(newValue)
                _resultChannel.send(newResult)
            } else if (storage.last() != newValue) {
                storage.add(newValue)
                _resultChannel.send(newResult)
            }
        } else {
            _resultChannel.send(newResult)
        }
    }
}