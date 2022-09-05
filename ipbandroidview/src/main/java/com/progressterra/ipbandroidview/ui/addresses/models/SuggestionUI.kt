package com.progressterra.ipbandroidview.ui.addresses.models

import com.progressterra.ipbandroidapi.api.suggestion.model.SuggestionExtendedInfo

data class SuggestionUI(
    val suggestionExtendedInfo: SuggestionExtendedInfo?,
    val previewOfSuggestion: String?
) {
    override fun toString(): String {
        return previewOfSuggestion ?: super.toString()
    }
}
