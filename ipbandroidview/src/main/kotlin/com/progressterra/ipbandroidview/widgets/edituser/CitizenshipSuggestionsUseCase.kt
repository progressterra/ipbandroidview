package com.progressterra.ipbandroidview.widgets.edituser

import com.progressterra.ipbandroidview.features.citizenshipsuggestions.CitizenshipSuggestionsState
import com.progressterra.ipbandroidview.processes.data.CitizenshipRepository
import com.progressterra.ipbandroidview.shared.AbstractLoggingUseCase

interface CitizenshipSuggestionsUseCase {

    suspend operator fun invoke(input: String): Result<CitizenshipSuggestionsState>

    class Base(
        private val repo: CitizenshipRepository
    ) : CitizenshipSuggestionsUseCase, AbstractLoggingUseCase() {

        override suspend fun invoke(input: String): Result<CitizenshipSuggestionsState> = handle {
            val suggestion = repo.citizenships()
                .firstOrNull { it.name.contains(input.trim(), ignoreCase = true) }
            CitizenshipSuggestionsState(
                suggestion = suggestion?.name ?: "",
                id = suggestion?.id ?: "",
                toHide = suggestion == null
            )
        }
    }
}