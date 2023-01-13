package com.progressterra.ipbandroidview.ui.organizations

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import com.progressterra.ipbandroidview.R
import com.progressterra.ipbandroidview.composable.OrganizationCard
import com.progressterra.ipbandroidview.composable.StateBox
import com.progressterra.ipbandroidview.composable.ThemedLayout
import com.progressterra.ipbandroidview.composable.ThemedTopAppBar
import com.progressterra.ipbandroidview.theme.AppTheme

@Composable
fun OrganizationsScreen(
    state: OrganizationsState,
    interactor: OrganizationsInteractor
) {
    ThemedLayout(topBar = {
        ThemedTopAppBar(title = stringResource(id = R.string.organizations))
    }) { _, _ ->
        StateBox(
            state = state.screenState,
            refresh = interactor::refresh
        ) {
            LazyColumn(
                modifier = Modifier.fillMaxSize(),
                verticalArrangement = Arrangement.spacedBy(AppTheme.dimensions.small),
                contentPadding = PaddingValues(AppTheme.dimensions.small)
            ) {
                items(state.organizations) {
                    OrganizationCard(
                        modifier = Modifier.fillMaxWidth(),
                        state = it,
                        openOrganization = interactor::onOrganizationDetails
                    )
                }
            }
        }
    }
}

@Preview
@Composable
private fun AuditsScreenPreviewSuccess() {
    AppTheme {
    }
}