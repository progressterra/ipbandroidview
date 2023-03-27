package com.progressterra.ipbandroidview.pages.proshkacart

import androidx.compose.foundation.layout.Column
import androidx.compose.runtime.Composable
import androidx.compose.runtime.Immutable
import com.progressterra.ipbandroidview.features.proshkatopbar.ProshkaTopBar
import com.progressterra.ipbandroidview.features.proshkatopbar.ProshkaTopBarState
import com.progressterra.ipbandroidview.shared.ui.StateBox
import com.progressterra.ipbandroidview.shared.ui.StateBoxState
import com.progressterra.ipbandroidview.shared.ui.ThemedLayout
import com.progressterra.ipbandroidview.features.proshkatopbar.UseProshkaTopBar
import com.progressterra.ipbandroidview.shared.ui.UseStateBox
import com.progressterra.ipbandroidview.widgets.proshkacartitems.ProshkaCartItems
import com.progressterra.ipbandroidview.widgets.proshkacartitems.ProshkaCartItemsState
import com.progressterra.ipbandroidview.widgets.proshkacartsummary.ProshkaCartSummary
import com.progressterra.ipbandroidview.widgets.proshkacartsummary.ProshkaCartSummaryState
import com.progressterra.ipbandroidview.widgets.proshkacartitems.UseProshkaCartItems
import com.progressterra.ipbandroidview.widgets.proshkacartsummary.UseProshkaCartSummary

@Immutable
data class ProshkaCartState(
    val stateBoxState: StateBoxState = StateBoxState(),
    val summaryState: ProshkaCartSummaryState = ProshkaCartSummaryState(),
    val items: ProshkaCartItemsState = ProshkaCartItemsState(),
    val topBar: ProshkaTopBarState = ProshkaTopBarState()
)

interface ProshkaCartScreenInteractor : UseProshkaCartItems, UseStateBox,
    UseProshkaTopBar, UseProshkaCartSummary

@Composable
fun ProshkaCartScreen(
    state: ProshkaCartState, interactor: ProshkaCartScreenInteractor
) {
    ThemedLayout(
        topBar = {
            ProshkaTopBar(
                state = state.topBar,
                useComponent = interactor
            )
        }
    ) { _, _ ->
        StateBox(
            state = state.stateBoxState, useComponent = interactor
        ) {
            Column {
                ProshkaCartItems(
                    state = state.items,
                    useComponent = interactor
                )
                ProshkaCartSummary(
                    state = state.summaryState,
                    useComponent = interactor
                )
            }
        }
    }
}