package com.progressterra.ipbandroidview.pages.documentdetails

import com.progressterra.ipbandroidview.shared.AbstractLoggingUseCase

interface ValidationUseCase {

    suspend operator fun invoke(state: DocumentDetailsState): Result<Unit>

    class Base : ValidationUseCase, AbstractLoggingUseCase() {

        override suspend fun invoke(state: DocumentDetailsState): Result<Unit> = handle {
            state.entries.forEach {
                if (!it.valid()) {
                    throw Exception("Invalid!")
                }
            }
            if (state.photo?.items?.isEmpty() == true) {
                throw Exception("Invalid!")
            }
        }
    }
}