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
        log("Cache", "With cache invoked")
        if (storage.isNotEmpty()) {
            log("Cache", "Storage is not empty")
            _resultFlow.emit(Result.success(storage.last()))
            log("Cache", "Result sent")
        }
        val newResult = withToken(block)
        if (newResult.isSuccess) {
            log("Cache", "Result is success")
            val newValue = newResult.getOrThrow()
            if (storage.isEmpty()) {
                log("Cache", "Storage is empty")
                storage.add(newValue)
                _resultFlow.emit(newResult)
            } else if (storage.last() != newValue) {
                log("Cache", "Storage is not empty and last value is not equal to new value")
                storage.add(newValue)
                _resultFlow.emit(newResult)
            }
        } else {
            log("Cache", "Result is failure")
            _resultFlow.emit(newResult)
        }
    }
}