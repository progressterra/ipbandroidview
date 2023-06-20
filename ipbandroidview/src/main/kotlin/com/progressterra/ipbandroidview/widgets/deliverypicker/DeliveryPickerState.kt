package com.progressterra.ipbandroidview.widgets.deliverypicker

import androidx.compose.runtime.Immutable
import com.progressterra.ipbandroidview.shared.ui.textfield.TextFieldState
import com.progressterra.processors.IpbSubState

@Immutable
data class DeliveryPickerState(
    @IpbSubState val city: TextFieldState = TextFieldState(id = "city"),
    @IpbSubState val home: TextFieldState = TextFieldState(id = "home"),
    @IpbSubState val entrance: TextFieldState = TextFieldState(id = "entrance"),
    @IpbSubState val apartment: TextFieldState = TextFieldState(id = "apartment"),
)