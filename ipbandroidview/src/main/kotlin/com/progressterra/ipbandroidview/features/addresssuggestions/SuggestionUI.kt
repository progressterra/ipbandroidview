package com.progressterra.ipbandroidview.features.addresssuggestions

import com.google.errorprone.annotations.Immutable
import com.progressterra.ipbandroidapi.api.suggestion.model.SuggestionExtendedInfo
import com.progressterra.ipbandroidview.shared.IsEmpty

@Immutable
data class SuggestionUI(
    val suggestionExtendedInfo: SuggestionExtendedInfo,
    val previewOfSuggestion: String
) : IsEmpty {

    override fun isEmpty(): Boolean =
        suggestionExtendedInfo == SuggestionExtendedInfo() && previewOfSuggestion == ""
}
