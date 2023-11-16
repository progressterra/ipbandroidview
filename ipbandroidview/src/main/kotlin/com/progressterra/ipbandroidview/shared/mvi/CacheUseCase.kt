package com.progressterra.ipbandroidview.shared.mvi

import kotlinx.coroutines.flow.Flow

interface CacheUseCase<T> {

    val resultFlow: Flow<Result<T>>
}