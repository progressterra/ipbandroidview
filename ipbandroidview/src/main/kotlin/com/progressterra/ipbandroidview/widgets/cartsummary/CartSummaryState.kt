package com.progressterra.ipbandroidview.widgets.cartsummary

import com.progressterra.ipbandroidview.entities.SimplePrice
import com.progressterra.ipbandroidview.shared.ui.button.ButtonState


data class CartSummaryState(
    val total: SimplePrice = SimplePrice(),
    val proceed: ButtonState = ButtonState()
)