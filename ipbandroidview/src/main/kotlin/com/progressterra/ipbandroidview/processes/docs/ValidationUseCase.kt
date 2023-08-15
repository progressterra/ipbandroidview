package com.progressterra.ipbandroidview.processes.docs

import com.progressterra.ipbandroidview.entities.Document
import com.progressterra.ipbandroidview.shared.AbstractLoggingUseCase

interface ValidationUseCase {

    suspend operator fun invoke(state: Document): Result<Unit>

    class Base : ValidationUseCase, AbstractLoggingUseCase() {

        override suspend fun invoke(state: Document): Result<Unit> = handle {
            state.entries.forEach {
                if (!it.valid()) {
                    throw Exception("Invalid!")
                }
            }
            if (state.photo.items.isEmpty() && state.photo.required) {
                throw Exception("Invalid!")
            }
        }
    }
}