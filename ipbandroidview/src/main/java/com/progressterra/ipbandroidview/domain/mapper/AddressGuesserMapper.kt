package com.progressterra.ipbandroidview.domain.mapper

import com.progressterra.ipbandroidapi.api.suggestion.model.SuggestionData
import com.progressterra.ipbandroidview.base.Mapper

interface AddressGuesserMapper : Mapper<SuggestionData, String> {

    class Base : AddressGuesserMapper {

        override fun map(data: SuggestionData): String =
            "${data.suggestionExtendedInfo.city}, ${data.suggestionExtendedInfo.street}, ${data.suggestionExtendedInfo.house}"
    }
}