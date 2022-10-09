package com.progressterra.ipbandroidview.domain.mapper

import com.progressterra.ipbandroidapi.api.suggestion.model.SuggestionData
import com.progressterra.ipbandroidview.core.Mapper

interface AddressGuesserMapper : Mapper<SuggestionData, String> {

    class Base : AddressGuesserMapper {

        override fun map(data: SuggestionData): String {
            if (data.suggestionExtendedInfo.city.isBlank() || data.suggestionExtendedInfo.street.isBlank() || data.suggestionExtendedInfo.house.isBlank())
                return ""
            return "${data.suggestionExtendedInfo.city}, ${data.suggestionExtendedInfo.street}, ${data.suggestionExtendedInfo.house}"
        }
    }
}