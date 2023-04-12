package com.progressterra.ipbandroidview.pages.cart

import androidx.compose.runtime.Immutable
import com.progressterra.ipbandroidview.shared.ScreenState
import com.progressterra.ipbandroidview.shared.ui.statebox.StateBoxState
import com.progressterra.ipbandroidview.widgets.proshkacartitems.ProshkaCartItemsState
import com.progressterra.ipbandroidview.widgets.proshkacartsummary.ProshkaCartSummaryState

@Immutable
data class CartState(
    val stateBox: StateBoxState = StateBoxState(),
    val summary: ProshkaCartSummaryState = ProshkaCartSummaryState(),
    val items: ProshkaCartItemsState = ProshkaCartItemsState()
) {

    fun updateScreenState(screenState: ScreenState) = copy(
        stateBox = stateBox.updateState(screenState)
    )
}