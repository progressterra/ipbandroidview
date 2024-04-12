package com.progressterra.ipbandroidview.widgets.cartsummary

import com.progressterra.ipbandroidview.entities.Price
import com.progressterra.ipbandroidview.shared.ui.button.ButtonState


data class CartSummaryState(
    val total: Price = Price(),
    val proceed: ButtonState = ButtonState()
)