package com.progressterra.ipbandroidview.domain.mapper

import com.progressterra.ipbandroidapi.api.suggestion.model.SuggestionData
import com.progressterra.ipbandroidview.base.Mapper
import com.progressterra.ipbandroidview.ui.city.Suggestion

interface SuggestionMapper : Mapper<SuggestionData, Suggestion> {

    class Base : SuggestionMapper {

        override fun map(data: SuggestionData): Suggestion = Suggestion(
            address = "${data.suggestionExtendedInfo.street}, ${data.suggestionExtendedInfo.house}",
            city = data.suggestionExtendedInfo.city
        )
    }
}