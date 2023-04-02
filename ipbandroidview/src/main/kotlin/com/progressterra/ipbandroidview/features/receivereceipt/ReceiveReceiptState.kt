package com.progressterra.ipbandroidview.features.receivereceipt

import androidx.compose.runtime.Immutable
import com.progressterra.ipbandroidview.shared.ui.brushedswitch.BrushedSwitchState
import com.progressterra.ipbandroidview.shared.ui.textfield.TextFieldState

@Immutable
data class ReceiveReceiptState(
    val receiveReceipt: BrushedSwitchState = BrushedSwitchState(),
    val emailState: TextFieldState = TextFieldState()
)