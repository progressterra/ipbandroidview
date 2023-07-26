package com.progressterra.ipbandroidview.features.dialogpicker

import androidx.compose.runtime.Immutable
import arrow.optics.optics
import com.progressterra.ipbandroidview.entities.Citizenship
import com.progressterra.ipbandroidview.shared.ui.button.ButtonState

@Immutable
@optics data class DialogPickerState(
    val variants: List<Citizenship> = emptyList(),
    val apply: ButtonState = ButtonState(
        id = "apply"
    ),
    val selected: Citizenship? = null,
    val open: Boolean = false
) { companion object }