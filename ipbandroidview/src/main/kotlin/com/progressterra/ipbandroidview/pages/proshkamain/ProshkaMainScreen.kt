package com.progressterra.ipbandroidview.pages.proshkamain

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.runtime.Composable
import androidx.compose.ui.unit.dp
import com.progressterra.ipbandroidview.features.ProshkaBonuses
import com.progressterra.ipbandroidview.shared.ui.StateBox
import com.progressterra.ipbandroidview.shared.ui.ThemedLayout
import com.progressterra.ipbandroidview.widgets.ProshkaGalleries
import com.progressterra.ipbandroidview.widgets.ProshkaOffers

@Composable
fun ProshkaMainScreen(
    state: ProshkaMainState, interactor: ProshkaMainScreenInteractor
) {
    ThemedLayout { _, _ ->
        StateBox(
            state = state.screenState, useComponent = interactor
        ) {
            Column(
                verticalArrangement = Arrangement.spacedBy(40.dp)
            ) {
                ProshkaBonuses(
                    state = state.bonuses, useComponent = interactor
                )
                ProshkaGalleries(
                    state = state.hits, useComponent = interactor
                )
                ProshkaOffers(
                    state = state.offers, useComponent = interactor
                )
                ProshkaGalleries(
                    state = state.new, useComponent = interactor
                )
            }
        }
    }
}