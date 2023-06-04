package com.progressterra.ipbandroidview.widgets.edituser

import com.progressterra.ipbandroidview.features.suggestions.SuggestionsState

interface CitizenshipSuggestionsUseCase {

    suspend operator fun invoke(input: String): SuggestionsState

    class Base : CitizenshipSuggestionsUseCase {

        override suspend fun invoke(input: String): SuggestionsState = SuggestionsState(
            items = if (input.length <= 3) emptyList() else listOf(
                SuggestionsState.Item(
                    name = "Россия",
                    data = "08db6340-c0c5-4b0b-8546-aeff7259b739"
                ),
                SuggestionsState.Item(
                    name = "Таджикистан",
                    data = "08db6340-a968-41d2-8d83-76a1db8ad366"
                ),
                SuggestionsState.Item(
                    name = "Украина",
                    data = "08db6340-a968-41d2-8d83-76a1db8ad366"
                ),
                SuggestionsState.Item(
                    name = "Узбекистан",
                    data = "08db6340-a968-41d2-8d83-76a1db8ad366"
                ),
                SuggestionsState.Item(
                    name = "Беларусь",
                    data = "08db6340-8152-4b24-8a32-776545e3240e"
                ),
                SuggestionsState.Item(
                    name = "Кыргызстан",
                    data = "08db6340-8152-4b24-8a32-776545e3240e"
                ),
                SuggestionsState.Item(
                    name = "Казахстан",
                    data = "08db6340-8152-4b24-8a32-776545e3240e"
                ),
                SuggestionsState.Item(
                    name = "Армения",
                    data = "08db6340-8152-4b24-8a32-776545e3240e"
                )
            ).filter { it.name.contains(input, true) }
        )
    }
}