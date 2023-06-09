package com.progressterra.ipbandroidview.widgets.documents

import androidx.compose.runtime.Immutable
import com.progressterra.ipbandroidapi.api.documents.models.TypeStatusDoc
import com.progressterra.ipbandroidview.entities.AdaptiveEntry
import com.progressterra.ipbandroidview.entities.Id
import com.progressterra.ipbandroidview.shared.ui.textfield.TextFieldState

@Immutable
data class DocumentsState(
    val items: List<Item> = emptyList()
) {

    @Immutable
    data class Item(
        override val id: String,
        val name: String,
        val status: TypeStatusDoc,
        val entries: List<TextFieldState>
    ) : Id
}