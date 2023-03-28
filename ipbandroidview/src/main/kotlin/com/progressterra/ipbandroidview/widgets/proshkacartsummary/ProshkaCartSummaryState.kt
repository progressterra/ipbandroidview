package com.progressterra.ipbandroidview.widgets.proshkacartsummary

import androidx.compose.runtime.Immutable
import com.progressterra.ipbandroidview.entities.SimplePrice
import com.progressterra.ipbandroidview.shared.ui.button.ButtonState

@Immutable
data class ProshkaCartSummaryState(
    val total: SimplePrice = SimplePrice(), val proceed: ButtonState = ButtonState()
)