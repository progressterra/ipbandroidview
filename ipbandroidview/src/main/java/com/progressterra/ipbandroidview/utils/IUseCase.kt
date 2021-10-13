package com.progressterra.ipbandroidview.utils

import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.StateFlow

interface IUseCase {
    interface InOut<T, V> {
        suspend fun invoke(param: T): V
    }

    interface Out<T> {
        suspend fun invoke(): T
    }

    interface In<T> {
        suspend fun invoke(param: T)
    }

    interface Invokable {
        suspend fun invoke()
    }

    interface FlowOut<T> {
        fun invoke(): Flow<T>
    }

    interface StateFlowOut<T> {
        fun invoke(): StateFlow<T>
    }

    interface FlowInOut<T, V> {
        fun invoke(param: T): Flow<V>
    }
}