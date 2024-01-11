package com.progressterra.ipbandroidview.entities

import com.progressterra.ipbandroidapi.api.suggestion.model.SuggestionExtendedInfo


data class SuggestionUI(
    val suggestionExtendedInfo: SuggestionExtendedInfo = SuggestionExtendedInfo(),
    val previewOfSuggestion: String = ""
) : IsEmpty {

    override fun isEmpty(): Boolean =
        suggestionExtendedInfo == SuggestionExtendedInfo() && previewOfSuggestion == ""
}
