package com.progressterra.ipbandroidview.domain.filter

import com.progressterra.ipbandroidapi.api.suggestion.model.SuggestionData

interface SuggestionFilter {

    fun filter(data: SuggestionData): Boolean

    class Base : SuggestionFilter {

        override fun filter(data: SuggestionData): Boolean =
            data.suggestionExtendedInfo.street.isNotBlank() && data.suggestionExtendedInfo.house.isNotBlank() && data.suggestionExtendedInfo.city.isNotBlank()
    }
}