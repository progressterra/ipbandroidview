package com.progressterra.ipbandroidview.ui.organizationaudits

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.progressterra.ipbandroidview.R
import com.progressterra.ipbandroidview.composable.OrganizationCheckCard
import com.progressterra.ipbandroidview.composable.OrganizationPresentation
import com.progressterra.ipbandroidview.composable.ThemedTopAppBar
import com.progressterra.ipbandroidview.theme.AppTheme

@Composable
fun OrganizationAuditsScreen(
    state: OrganizationAuditsState,
    interactor: OrganizationAuditsInteractor
) {
    Scaffold(topBar = {
        ThemedTopAppBar(
            onBack = { interactor.onBack() },
            title = stringResource(id = R.string.audits)
        )
    }) {
        LazyColumn(
            modifier = Modifier
                .fillMaxSize()
                .background(AppTheme.colors.background)
                .padding(top = 8.dp, start = 8.dp, end = 8.dp),
            verticalArrangement = Arrangement.spacedBy(8.dp)
        ) {
            item {
                OrganizationPresentation(
                    name = state.organizationName,
                    address = state.organizationAddress,
                    imageUrl = state.imageUrl,
                    onMapClick = { interactor.onMapClick() }
                )
            }
            items(state.audits) {
                OrganizationCheckCard(name = it.name, lastTime = it.lastTime, warning = it.warning)
            }
        }
    }
}

@Preview
@Composable
private fun OrganizationAuditsScreenPreview() {
    AppTheme {
        OrganizationAuditsScreen(
            state = OrganizationAuditsState(),
            interactor = OrganizationAuditsInteractor.Empty()
        )
    }
}

