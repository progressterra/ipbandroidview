package com.progressterra.ipbandroidview.features.suggestions

interface UseSuggestions<T : Any> {

    fun handle(event: SuggestionsEvent<T>)

    class Empty<T : Any> : UseSuggestions<T> {

        override fun handle(event: SuggestionsEvent<T>) = Unit
    }
}