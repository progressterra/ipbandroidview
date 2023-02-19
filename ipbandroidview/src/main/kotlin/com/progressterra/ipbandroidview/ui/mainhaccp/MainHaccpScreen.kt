package com.progressterra.ipbandroidview.ui.mainhaccp

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.progressterra.ipbandroidview.composable.OrganizationsOverview
import com.progressterra.ipbandroidview.composable.PartnerBlock
import com.progressterra.ipbandroidview.composable.StateBox
import com.progressterra.ipbandroidview.composable.ThemedLayout
import com.progressterra.ipbandroidview.theme.AppTheme

@Composable
fun MainHaccpScreen(
    state: MainHaccpState,
    interactor: MainHaccpInteractor
) {
    ThemedLayout(
        topBar = {
            PartnerBlock(
                partner = state.partner,
                onEvent = { interactor.handleEvent(it) }
            )
        }
    ) { _, _ ->
        StateBox(
            state = state.screenState, refresh = { interactor.refresh() }
        ) {
            Column(
                Modifier
                    .fillMaxSize()
                    .padding(top = AppTheme.dimensions.small)
            ) {
                OrganizationsOverview(
                    overviews = state.overviews,
                    onEvent = { interactor.handleEvent(it) }
                )
            }
        }
    }
}