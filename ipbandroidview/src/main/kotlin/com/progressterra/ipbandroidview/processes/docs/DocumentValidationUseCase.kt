package com.progressterra.ipbandroidview.processes.docs

import com.progressterra.ipbandroidview.entities.Document

interface DocumentValidationUseCase {

    suspend operator fun invoke(state: Document): Result<Unit>

    class Base : DocumentValidationUseCase {

        override suspend fun invoke(state: Document): Result<Unit> = runCatching {
            state.entries.forEach {
                if (!it.valid()) {
                    throw Exception("Invalid!")
                }
            }
            if (state.photo.isEmpty() && state.photo.required) {
                throw Exception("Invalid!")
            }
        }
    }
}