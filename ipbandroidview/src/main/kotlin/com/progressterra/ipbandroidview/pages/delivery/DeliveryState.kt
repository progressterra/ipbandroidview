package com.progressterra.ipbandroidview.pages.delivery

import androidx.compose.runtime.Immutable
import arrow.optics.optics
import com.progressterra.ipbandroidview.entities.AddressUI
import com.progressterra.ipbandroidview.features.addresssuggestions.SuggestionUI
import com.progressterra.ipbandroidview.shared.ScreenState
import com.progressterra.ipbandroidview.shared.ui.button.ButtonState
import com.progressterra.ipbandroidview.shared.ui.textfield.TextFieldState
import com.progressterra.ipbandroidview.widgets.deliverypicker.DeliveryPickerState
import com.progressterra.processors.IpbSubState

@Immutable
@optics
data class DeliveryState(
    val deliveryPicker: DeliveryPickerState = DeliveryPickerState(),
    @IpbSubState val commentary: TextFieldState = TextFieldState(
        id = "commentary"
    ),
    @IpbSubState val confirm: ButtonState = ButtonState(
        id = "confirm"
    ),
    val address: AddressUI = AddressUI(),
    val suggestion: SuggestionUI = SuggestionUI(),
    val screenState: ScreenState = ScreenState.LOADING
) { companion object }