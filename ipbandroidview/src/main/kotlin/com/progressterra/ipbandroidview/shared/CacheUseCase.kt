package com.progressterra.ipbandroidview.shared

import kotlinx.coroutines.flow.Flow

interface CacheUseCase<T> {

    val resultFlow: Flow<Result<T>>
}