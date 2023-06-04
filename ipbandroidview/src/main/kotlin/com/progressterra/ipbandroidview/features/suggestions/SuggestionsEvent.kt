package com.progressterra.ipbandroidview.features.suggestions

import com.progressterra.ipbandroidview.entities.Suggestion

sealed class SuggestionsEvent<T : Any>(val suggestion: Suggestion<T>) {

    class Click<T : Any>(suggestion: Suggestion<T>) : SuggestionsEvent<T>(suggestion)
}