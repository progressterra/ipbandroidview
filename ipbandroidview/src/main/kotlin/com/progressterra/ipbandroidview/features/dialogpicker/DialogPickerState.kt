package com.progressterra.ipbandroidview.features.dialogpicker

import androidx.compose.runtime.Immutable
import com.progressterra.ipbandroidview.entities.Citizenship
import com.progressterra.ipbandroidview.shared.ui.button.ButtonState

@Immutable
data class DialogPickerState(
    val variants: List<Citizenship> = emptyList(),
    val apply: ButtonState = ButtonState(
        id = "apply"
    ),
    val selected: Citizenship? = null,
    val open: Boolean = false
)