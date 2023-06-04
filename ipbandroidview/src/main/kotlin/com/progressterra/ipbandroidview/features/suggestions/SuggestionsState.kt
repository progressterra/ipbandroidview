package com.progressterra.ipbandroidview.features.suggestions

import androidx.compose.runtime.Immutable
import com.progressterra.ipbandroidview.entities.Suggestion
import com.progressterra.processors.IpbState


@Immutable
data class SuggestionsState(
    val items: List<Item> = emptyList()
) {

    @Immutable
    data class Item(
        override val name: String, override val data: String
    ) : Suggestion<String>
}

