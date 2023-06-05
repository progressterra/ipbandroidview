package com.progressterra.ipbandroidview.features.citizenshipsuggestions

interface UseCitizenshipSuggestions {

    fun handle(event: CitizenshipSuggestionsEvent)

    class Empty: UseCitizenshipSuggestions {

        override fun handle(event: CitizenshipSuggestionsEvent) = Unit
    }
}