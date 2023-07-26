package com.progressterra.ipbandroidview.widgets.cartsummary

import androidx.compose.runtime.Immutable
import arrow.optics.optics
import com.progressterra.ipbandroidview.entities.SimplePrice
import com.progressterra.ipbandroidview.shared.ui.button.ButtonState

@Immutable
@optics data class CartSummaryState(
    val total: SimplePrice = SimplePrice(),
    val proceed: ButtonState = ButtonState()
) { companion object }