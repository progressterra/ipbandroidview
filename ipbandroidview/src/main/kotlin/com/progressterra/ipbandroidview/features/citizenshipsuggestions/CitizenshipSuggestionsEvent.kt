package com.progressterra.ipbandroidview.features.citizenshipsuggestions

sealed class CitizenshipSuggestionsEvent(val suggestion: CitizenshipSuggestionsState.Item) {

    class Click(suggestion: CitizenshipSuggestionsState.Item) : CitizenshipSuggestionsEvent(suggestion)
}