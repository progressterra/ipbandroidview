package com.progressterra.ipbandroidview.pages.cart

import androidx.compose.runtime.Immutable
import com.progressterra.ipbandroidview.widgets.proshkacartitems.ProshkaCartItemsState
import com.progressterra.ipbandroidview.widgets.proshkacartsummary.ProshkaCartSummaryState

@Immutable
data class CartState(
    val goods: ProshkaCartItemsState = ProshkaCartItemsState(),
    val summary: ProshkaCartSummaryState = ProshkaCartSummaryState()
)