package com.progressterra.ipbandroidview.ui.organizationaudits

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import com.progressterra.ipbandroidview.R
import com.progressterra.ipbandroidview.composable.ThemedTopAppBar
import com.progressterra.ipbandroidview.composable.OrganizationCheckCard
import com.progressterra.ipbandroidview.composable.OrganizationPresentation
import com.progressterra.ipbandroidview.shared.ui.ThemedLayout
import com.progressterra.ipbandroidview.shared.ui.statebox.StateBox
import com.progressterra.ipbandroidview.shared.theme.IpbTheme

@Composable
fun OrganizationAuditsScreen(
    state: OrganizationAuditsState,
    interactor: OrganizationAuditsInteractor
) {
    ThemedLayout(topBar = {
        ThemedTopAppBar(
            onBack = { interactor.onBack() }, title = stringResource(id = R.string.organization)
        )
    }) { _, _ ->
        StateBox(
            refresh = { interactor.refresh() },
            state = state.screenState
        ) {
            LazyColumn(
                modifier = Modifier
                    .fillMaxSize(),
                verticalArrangement = Arrangement.spacedBy(8.dp),
                contentPadding = PaddingValues(8.dp)
            ) {
                item {
                    OrganizationPresentation(
                        name = state.organizationName,
                        address = state.organizationAddress,
                        imageUrl = state.imageUrl,
                        onMapClick = { interactor.mapClick() }
                    )
                }
                items(state.audits) {
                    OrganizationCheckCard(
                        name = it.name,
                        lastTime = it.lastTime,
                        onClick = { interactor.onAuditDetails(it) }
                    )
                }
            }
        }
    }
}

@Preview
@Composable
private fun OrganizationAuditsScreenPreview() {
    IpbTheme {
    }
}