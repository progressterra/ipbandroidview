package com.progressterra.ipbandroidview.ui.addresses.models

import com.progressterra.ipbandroidapi.remoteData.scrm.models.address.dadata.SuggestionExtendedInfo

data class SuggestionUI(
    val suggestionExtendedInfo: SuggestionExtendedInfo?,
    val previewOfSuggestion: String?
) {
    override fun toString(): String {
        return previewOfSuggestion ?: super.toString()
    }
}
