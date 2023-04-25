package com.progressterra.ipbandroidview.features.editbutton

import androidx.compose.runtime.Immutable
import com.progressterra.ipbandroidview.shared.ui.button.ButtonState

@Immutable
data class EditButtonState(
    val edit: ButtonState = ButtonState(
        id = "edit"
    ),
    val save: ButtonState = ButtonState(
        id = "save"
    ),
    val cancel: ButtonState = ButtonState(
        id = "cancel"
    ),
    val editing: Boolean = false
) {

    fun startCancel() = copy(editing = !editing)

    fun uSaveEnabled(enabled: Boolean) = copy(save = save.uEnabled(enabled))
}