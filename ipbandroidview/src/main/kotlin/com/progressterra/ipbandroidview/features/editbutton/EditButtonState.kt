package com.progressterra.ipbandroidview.features.editbutton

import com.progressterra.ipbandroidview.shared.ui.button.ButtonState


data class EditButtonState(
    val edit: ButtonState = ButtonState(id = "edit"),
    val save: ButtonState = ButtonState(id = "save"),
    val cancel: ButtonState = ButtonState(id = "cancel"),
    val editing: Boolean = false
) {
    companion object
}