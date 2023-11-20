package com.progressterra.ipbandroidview.processes.docs

import com.progressterra.ipbandroidview.entities.DocsVerificationPolicy
import com.progressterra.ipbandroidview.entities.Document

interface DocumentValidationUseCase {

    suspend operator fun invoke(state: Document, policy: DocsVerificationPolicy): Result<Unit>

    class Base : DocumentValidationUseCase {

        override suspend fun invoke(state: Document, policy: DocsVerificationPolicy): Result<Unit> = runCatching {
            state.entries.forEach {
                if (!it.valid()) {
                    throw Exception("Invalid!")
                }
            }
            val allFieldsEmpty = state.entries.all { it.formatByType().isEmpty() }
            val photoIsEmpty = state.photo.isEmpty()
            when (policy) {
                DocsVerificationPolicy.PHOTO_AND_TEXT -> {
                    if (allFieldsEmpty || photoIsEmpty) {
                        throw Exception("Invalid!")
                    }
                }
                DocsVerificationPolicy.PHOTO_OR_TEXT -> {
                    if (allFieldsEmpty && photoIsEmpty) {
                        throw Exception("Invalid!")
                    }
                }
            }
        }
    }
}