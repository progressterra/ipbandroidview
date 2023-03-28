package com.progressterra.ipbandroidview.ui.organizations

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.onGloballyPositioned
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.progressterra.ipbandroidview.R
import com.progressterra.ipbandroidview.composable.OrganizationCard
import com.progressterra.ipbandroidview.composable.PartnerBlock2
import com.progressterra.ipbandroidview.shared.ui.StateBox
import com.progressterra.ipbandroidview.shared.ui.ThemedLayout
import com.progressterra.ipbandroidview.composable.ThemedTopAppBar
import com.progressterra.ipbandroidview.core.ScreenState
import com.progressterra.ipbandroidview.entities.Organization
import com.progressterra.ipbandroidview.entities.Partner
import com.progressterra.ipbandroidview.shared.theme.IpbTheme

@Composable
fun OrganizationsScreen(
    state: OrganizationsState, interactor: OrganizationsInteractor
) {
    ThemedLayout(topBar = {
        ThemedTopAppBar(title = stringResource(id = R.string.organizations))
    }) { _, _ ->
        StateBox(state = state.screenState, refresh = { interactor.refresh() }) {
            var bannerWidth by remember { mutableStateOf(0.dp) }
            var bannerHeight by remember { mutableStateOf(0.dp) }
            val density = LocalDensity.current
            LazyColumn(
                modifier = Modifier.fillMaxSize(),
                verticalArrangement = Arrangement.spacedBy(8.dp),
                contentPadding = PaddingValues(8.dp)
            ) {
                item {
                    PartnerBlock2(
                        modifier = Modifier.size(bannerWidth, bannerHeight),
                        partner = state.partner,
                        onEvent = { interactor.handle(it) }
                    )
                }
                items(state.organizations) {
                    OrganizationCard(
                        modifier = Modifier
                            .fillMaxWidth()
                            .onGloballyPositioned {
                                with(density) {
                                    bannerWidth = it.size.width.toDp()
                                    bannerHeight = it.size.height.toDp()
                                }
                            },
                        state = it,
                        openOrganization = { interactor.onOrganizationDetails(it) }
                    )
                }
            }
        }
    }
}

@Preview
@Composable
private fun AuditsScreenPreviewSuccess() {
    IpbTheme {
        OrganizationsScreen(
            state = OrganizationsState(
                organizations = listOf(
                    Organization(
                        address = "Some address",
                        id = "",
                        audits = "1",
                        documents = "2",
                        name = "Some org",
                        imageUrl = "",
                        latitude = 0.0,
                        longitude = 0.0
                    ), Organization(
                        address = "Some address 235",
                        id = "",
                        audits = "11",
                        documents = "222",
                        name = "Some org 2",
                        imageUrl = "",
                        latitude = 0.0,
                        longitude = 0.0
                    )
                ), partner = Partner(), screenState = ScreenState.SUCCESS
            ), interactor = OrganizationsInteractor.Empty()
        )
    }
}