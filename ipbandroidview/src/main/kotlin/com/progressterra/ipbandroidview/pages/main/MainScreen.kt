package com.progressterra.ipbandroidview.pages.main

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.progressterra.ipbandroidview.R
import com.progressterra.ipbandroidview.features.bonuses.Bonuses
import com.progressterra.ipbandroidview.shared.ui.ThemedLayout
import com.progressterra.ipbandroidview.shared.ui.statebox.StateBox
import com.progressterra.ipbandroidview.widgets.galleries.Galleries
import com.progressterra.ipbandroidview.widgets.offers.Offers

@Composable
fun MainScreen(
    state: MainState, useComponent: UseMain
) {
    ThemedLayout { _, _ ->
        StateBox(
            state = state.stateBox, useComponent = useComponent
        ) {
            Column(
                modifier = Modifier.verticalScroll(rememberScrollState()),
                verticalArrangement = Arrangement.spacedBy(40.dp)
            ) {
                Bonuses(
                    state = state.bonuses, useComponent = useComponent
                )
                Galleries(
                    state = state.hits, useComponent = useComponent,
                    title = stringResource(R.string.hits)
                )
                Offers(
                    state = state.offers
                )
                Galleries(
                    state = state.new, useComponent = useComponent,
                    title = stringResource(R.string.new_items)
                )
            }
        }
    }
}