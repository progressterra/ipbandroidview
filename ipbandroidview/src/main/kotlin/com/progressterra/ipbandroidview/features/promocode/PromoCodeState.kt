package com.progressterra.ipbandroidview.features.promocode

import com.progressterra.ipbandroidview.entities.SimplePrice
import com.progressterra.ipbandroidview.shared.ui.textfield.TextFieldState


data class PromoCodeState(
    val price: SimplePrice = SimplePrice(),
    val code: TextFieldState = TextFieldState()
)