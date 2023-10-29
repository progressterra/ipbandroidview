package com.progressterra.ipbandroidview.processes.docs

import com.progressterra.ipbandroidview.entities.Document
import com.progressterra.ipbandroidview.processes.utils.MakeToastUseCase
import com.progressterra.ipbandroidview.shared.AbstractLoggingUseCase
import com.progressterra.ipbandroidview.shared.ManageResources

interface DocumentValidationUseCase {

    suspend operator fun invoke(state: Document): Result<Unit>

    class Base(makeToastUseCase: MakeToastUseCase, manageResources: ManageResources) : DocumentValidationUseCase, AbstractLoggingUseCase(
        makeToastUseCase, manageResources
    ) {

        override suspend fun invoke(state: Document): Result<Unit> = handle {
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