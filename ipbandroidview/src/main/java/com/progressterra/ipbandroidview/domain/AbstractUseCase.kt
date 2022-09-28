package com.progressterra.ipbandroidview.domain

abstract class AbstractUseCase {

    protected suspend fun <T> handle(block: suspend () -> T): Result<T> = try {
        Result.success(block.invoke())
    } catch (exception: Exception) {
        Result.failure(exception)
    }
}