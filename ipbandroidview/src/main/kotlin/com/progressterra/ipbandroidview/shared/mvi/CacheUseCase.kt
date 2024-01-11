package com.progressterra.ipbandroidview.shared.mvi

import kotlinx.coroutines.flow.Flow

/**
 * Interface for cache use cases' results
 */
interface CacheUseCase<T> {

    val resultFlow: Flow<Result<T>>
}