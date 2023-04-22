package com.progressterra.ipbandroidview.features.receivereceipt

import androidx.compose.runtime.Immutable
import com.progressterra.ipbandroidview.shared.ui.brushedswitch.BrushedSwitchState
import com.progressterra.ipbandroidview.shared.ui.textfield.TextFieldState

@Immutable
data class ReceiveReceiptState(
    val receiveReceipt: BrushedSwitchState = BrushedSwitchState(
        id = "receiveReceipt"
    ),
    val emailState: TextFieldState = TextFieldState(
        id = "email"
    )
) {

    fun reverseReceiveReceipt() = copy(receiveReceipt = receiveReceipt.reverse())

    fun updateEmail(newEmail: String) = copy(emailState = emailState.updateText(newEmail))
}