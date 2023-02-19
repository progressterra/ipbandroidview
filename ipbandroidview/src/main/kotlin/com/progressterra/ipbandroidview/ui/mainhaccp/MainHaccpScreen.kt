package com.progressterra.ipbandroidview.ui.mainhaccp

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.progressterra.ipbandroidview.composable.OrganizationOverview
import com.progressterra.ipbandroidview.composable.OrganizationsOverview
import com.progressterra.ipbandroidview.composable.PartnerBlock
import com.progressterra.ipbandroidview.composable.StateBox
import com.progressterra.ipbandroidview.composable.ThemedLayout
import com.progressterra.ipbandroidview.core.ScreenState
import com.progressterra.ipbandroidview.model.Partner
import com.progressterra.ipbandroidview.theme.AppTheme

@Composable
fun MainHaccpScreen(
    state: MainHaccpState, interactor: MainHaccpInteractor
) {
    ThemedLayout(topBar = {
        StateBox(
            state = state.screenState
        ) {
            PartnerBlock(partner = state.partner, onEvent = { interactor.handleEvent(it) })
        }
    }) { _, _ ->
        StateBox(state = state.screenState, refresh = { interactor.refresh() }) {
            Column(
                Modifier
                    .fillMaxSize()
                    .padding(AppTheme.dimensions.small)
            ) {
                OrganizationsOverview(overviews = state.overviews,
                    onEvent = { interactor.handleEvent(it) })
            }
        }
    }
}

@Preview
@Composable
private fun MainHaccpScreenPreview() {
    AppTheme {
        MainHaccpScreen(
            state = MainHaccpState(
                screenState = ScreenState.SUCCESS, partner = Partner(), overviews = listOf(
                    OrganizationOverview(
                        name = "Organization",
                        ongoing = emptyList(),
                        completed = emptyList(),
                        archived = emptyList()
                    )
                )
            ), interactor = MainHaccpInteractor.Empty()
        )
    }
}