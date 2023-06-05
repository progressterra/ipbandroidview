package com.progressterra.ipbandroidview.widgets.edituser

import com.progressterra.ipbandroidview.features.citizenshipsuggestions.CitizenshipSuggestionsState
import com.progressterra.ipbandroidview.processes.data.CitizenshipRepository

interface CitizenshipSuggestionsUseCase {

    suspend operator fun invoke(input: String): CitizenshipSuggestionsState

    class Base(
        private val repo: CitizenshipRepository
    ) : CitizenshipSuggestionsUseCase {

        override suspend fun invoke(input: String): CitizenshipSuggestionsState =
            CitizenshipSuggestionsState(
                items = if (input.length <= 3) emptyList() else repo.provideCitizenships()
                    .filter { it.name.contains(input, true) }
                    .map { CitizenshipSuggestionsState.Item(it.name, it.id) }
            )
    }
}