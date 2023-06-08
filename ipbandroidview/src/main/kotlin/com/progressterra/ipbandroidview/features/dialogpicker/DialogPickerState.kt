package com.progressterra.ipbandroidview.features.dialogpicker

import androidx.compose.runtime.Immutable
import com.progressterra.ipbandroidview.entities.Id
import com.progressterra.ipbandroidview.shared.ui.button.ButtonState

@Immutable
data class DialogPickerState(
    val variants: List<Item> = emptyList(),
    val apply: ButtonState = ButtonState(
        id = "apply"
    ),
    val selected: Item? = null
) {

    @Immutable
    data class Item(
        override val id: String,
        val text: String
    ) : Id
}