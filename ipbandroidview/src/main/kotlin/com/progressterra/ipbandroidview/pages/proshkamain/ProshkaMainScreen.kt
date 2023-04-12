package com.progressterra.ipbandroidview.pages.proshkamain

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.progressterra.ipbandroidview.R
import com.progressterra.ipbandroidview.features.proshkabonuses.ProshkaBonuses
import com.progressterra.ipbandroidview.shared.ui.statebox.StateBox
import com.progressterra.ipbandroidview.shared.ui.ThemedLayout
import com.progressterra.ipbandroidview.widgets.proshkagalleries.ProshkaGalleries
import com.progressterra.ipbandroidview.widgets.proshkaoffers.ProshkaOffers

@Composable
fun ProshkaMainScreen(
    state: ProshkaMainState, useComponent: UseProshkaMain
) {
    ThemedLayout { _, _ ->
        StateBox(
            state = state.stateBox, useComponent = useComponent
        ) {
            Column(
                modifier = Modifier.verticalScroll(rememberScrollState()),
                verticalArrangement = Arrangement.spacedBy(40.dp)
            ) {
                ProshkaBonuses(
                    modifier = Modifier.padding(horizontal = 20.dp),
                    state = state.bonuses, useComponent = useComponent
                )
                ProshkaGalleries(
                    state = state.hits, useComponent = useComponent,
                    title = stringResource(R.string.hits)
                )
                ProshkaOffers(
                    state = state.offers, useComponent = useComponent
                )
                ProshkaGalleries(
                    state = state.new, useComponent = useComponent,
                    title = stringResource(R.string.new_items)
                )
            }
        }
    }
}