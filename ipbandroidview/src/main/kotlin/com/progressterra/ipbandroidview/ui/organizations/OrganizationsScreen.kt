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
import com.progressterra.ipbandroidview.components.OrganizationCard
import com.progressterra.ipbandroidview.components.StateBox
import com.progressterra.ipbandroidview.components.ThemedLayout
import com.progressterra.ipbandroidview.components.topbar.ThemedTopAppBar
import com.progressterra.ipbandroidview.theme.AppTheme

@Composable
fun OrganizationsScreen(
    state: () -> OrganizationsState,
    refresh: () -> Unit,
    organizationDetails: (Organization) -> Unit
) {
    ThemedLayout(topBar = {
        ThemedTopAppBar(title = { stringResource(id = R.string.organizations) })
    }) { _, _ ->
        StateBox(
            state = state()::screenState,
            onRefresh = refresh
        ) {
            LazyColumn(
                modifier = Modifier
                    .fillMaxSize(),
                verticalArrangement = Arrangement.spacedBy(AppTheme.dimensions.medium),
                contentPadding = PaddingValues(AppTheme.dimensions.medium)
            ) {
                items(state().organizations) {
                    OrganizationCard(modifier = Modifier.fillMaxWidth(),
                        address = it::address,
                        description = it::name,
                        onClick = { organizationDetails(it) }
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