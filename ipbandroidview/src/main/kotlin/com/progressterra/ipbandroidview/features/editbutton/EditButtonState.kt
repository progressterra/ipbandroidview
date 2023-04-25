package com.progressterra.ipbandroidview.features.editbutton

import androidx.compose.runtime.Immutable
import com.progressterra.ipbandroidview.shared.ui.button.ButtonState
import com.progressterra.processors.IpbSubState

@Immutable
data class EditButtonState(
    @IpbSubState val edit: ButtonState = ButtonState(
        id = "edit"
    ),
    @IpbSubState val save: ButtonState = ButtonState(
        id = "save"
    ),
    @IpbSubState val cancel: ButtonState = ButtonState(
        id = "cancel"
    ),
    val editing: Boolean = false
) {

    fun startCancel() = copy(editing = !editing)
}