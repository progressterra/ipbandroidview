package com.progressterra.ipbandroidview.features.receivereceipt

import androidx.compose.runtime.Immutable
import com.progressterra.ipbandroidview.shared.ui.brushedswitch.BrushedSwitchState
import com.progressterra.ipbandroidview.shared.ui.textfield.TextFieldState
import com.progressterra.processors.IpbSubState

@Immutable
data class ReceiveReceiptState(
    val receiveReceipt: BrushedSwitchState = BrushedSwitchState(
        id = "receiveReceipt"
    ),
    @IpbSubState val emailState: TextFieldState = TextFieldState(
        id = "email"
    )
) {

    fun reverseReceiveReceipt() = copy(receiveReceipt = receiveReceipt.reverse())
}