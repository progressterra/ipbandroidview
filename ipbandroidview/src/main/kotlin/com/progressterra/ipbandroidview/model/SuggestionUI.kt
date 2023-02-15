package com.progressterra.ipbandroidview.model

import com.progressterra.ipbandroidapi.api.suggestion.model.SuggestionExtendedInfo
import com.progressterra.ipbandroidview.core.IsEmpty

data class SuggestionUI(
    val suggestionExtendedInfo: SuggestionExtendedInfo,
    val previewOfSuggestion: String
) : IsEmpty {

    override fun isEmpty(): Boolean =
        suggestionExtendedInfo == SuggestionExtendedInfo() && previewOfSuggestion == ""

    override fun toString(): String {
        return previewOfSuggestion
    }
}
