package com.progressterra.ipbandroidview.pages.proshkacart

import androidx.compose.foundation.layout.Column
import androidx.compose.runtime.Composable
import androidx.compose.ui.res.stringResource
import com.progressterra.ipbandroidview.R
import com.progressterra.ipbandroidview.features.proshkatopbar.ProshkaTopBar
import com.progressterra.ipbandroidview.shared.ui.ThemedLayout
import com.progressterra.ipbandroidview.shared.ui.statebox.StateBox
import com.progressterra.ipbandroidview.widgets.proshkacartitems.ProshkaCartItems
import com.progressterra.ipbandroidview.widgets.proshkacartsummary.ProshkaCartSummary

@Composable
fun ProshkaCartScreen(
    state: ProshkaCartState, useComponent: UseProshkaCartScreen
) {
    ThemedLayout(
        topBar = {
            ProshkaTopBar(
                title = stringResource(R.string.cart),
                useComponent = useComponent
            )
        }
    ) { _, _ ->
        StateBox(
            state = state.stateBoxState, useComponent = useComponent
        ) {
            Column {
                ProshkaCartItems(
                    state = state.items,
                    useComponent = useComponent
                )
                ProshkaCartSummary(
                    state = state.summaryState,
                    useComponent = useComponent
                )
            }
        }
    }
}