package com.progressterra.ipbandroidview.entities

import com.progressterra.ipbandroidapi.api.suggestion.model.SuggestionExtendedInfo
import com.progressterra.ipbandroidview.shared.IsEmpty

data class SuggestionUI(
    val suggestionExtendedInfo: SuggestionExtendedInfo,
    val previewOfSuggestion: String
) : IsEmpty {

    override fun isEmpty(): Boolean =
        suggestionExtendedInfo == SuggestionExtendedInfo() && previewOfSuggestion == ""
}
