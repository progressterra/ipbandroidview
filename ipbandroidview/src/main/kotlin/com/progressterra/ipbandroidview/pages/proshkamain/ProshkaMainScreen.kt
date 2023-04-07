package com.progressterra.ipbandroidview.pages.proshkamain

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.runtime.Composable
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
                verticalArrangement = Arrangement.spacedBy(40.dp)
            ) {
                ProshkaBonuses(
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