package com.progressterra.ipbandroidview.pages.proshkacart

import androidx.compose.runtime.Immutable
import com.progressterra.ipbandroidview.shared.ui.statebox.StateBoxState
import com.progressterra.ipbandroidview.widgets.proshkacartitems.ProshkaCartItemsState
import com.progressterra.ipbandroidview.widgets.proshkacartsummary.ProshkaCartSummaryState

@Immutable
data class ProshkaCartState(
    val stateBoxState: StateBoxState = StateBoxState(),
    val summaryState: ProshkaCartSummaryState = ProshkaCartSummaryState(),
    val items: ProshkaCartItemsState = ProshkaCartItemsState()
)