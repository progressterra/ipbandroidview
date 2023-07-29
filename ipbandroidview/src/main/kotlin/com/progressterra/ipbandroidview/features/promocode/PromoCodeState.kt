package com.progressterra.ipbandroidview.features.promocode

import androidx.compose.runtime.Immutable
import com.progressterra.ipbandroidview.entities.SimplePrice
import com.progressterra.ipbandroidview.shared.ui.textfield.TextFieldState

@Immutable
data class PromoCodeState(
    val price: SimplePrice = SimplePrice(),
    val code: TextFieldState = TextFieldState()
)