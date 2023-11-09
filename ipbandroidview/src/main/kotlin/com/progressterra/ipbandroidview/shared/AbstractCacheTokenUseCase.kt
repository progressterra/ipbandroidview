package com.progressterra.ipbandroidview.shared

import com.progressterra.ipbandroidview.processes.ObtainAccessToken
import com.progressterra.ipbandroidview.processes.utils.MakeToastUseCase
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableSharedFlow

abstract class AbstractCacheTokenUseCase<T>(
    obtainAccessToken: ObtainAccessToken, makeToastUseCase: MakeToastUseCase,
    manageResources: ManageResources
) : AbstractTokenUseCase(obtainAccessToken, makeToastUseCase, manageResources), CacheUseCase<T> {

    private val storage: MutableList<T> = mutableListOf()

    private val _resultFlow = MutableSharedFlow<Result<T>>()
    override val resultFlow: Flow<Result<T>> = _resultFlow

    protected suspend fun withCache(
        block: suspend (accessToken: String) -> T
    ) {
        if (storage.isNotEmpty()) {
            _resultFlow.emit(Result.success(storage.last()))
        }
        val newResult = withToken(block)
        if (newResult.isSuccess) {
            val newValue = newResult.getOrThrow()
            if (storage.isEmpty()) {
                storage.add(newValue)
                _resultFlow.emit(newResult)
            } else if (storage.last() != newValue) {
                storage.add(newValue)
                _resultFlow.emit(newResult)
            }
        } else {
            _resultFlow.emit(newResult)
        }
    }
}