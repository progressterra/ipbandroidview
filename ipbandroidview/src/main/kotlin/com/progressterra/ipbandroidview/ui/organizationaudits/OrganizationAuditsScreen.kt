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
import com.progressterra.ipbandroidview.composable.component.ThemedTopAppBar
import com.progressterra.ipbandroidview.composable.component.OrganizationCheckCard
import com.progressterra.ipbandroidview.composable.component.OrganizationPresentation
import com.progressterra.ipbandroidview.composable.component.ThemedLayout
import com.progressterra.ipbandroidview.composable.element.StateBox
import com.progressterra.ipbandroidview.theme.AppTheme

@Composable
fun OrganizationAuditsScreen(
    state: () -> OrganizationAuditsState,
    onMapClick: () -> Unit,
    back: () -> Unit,
    refresh: () -> Unit,
    auditDetails: (OrganizationAudit) -> Unit
) {
    ThemedLayout(topBar = {
        ThemedTopAppBar(
            onBack = back, title = stringResource(id = R.string.organization)
        )
    }) { _, _ ->
        StateBox(
            refresh = refresh,
            state = state()::screenState
        ) {
            LazyColumn(
                modifier = Modifier
                    .fillMaxSize(),
                verticalArrangement = Arrangement.spacedBy(AppTheme.dimensions.small),
                contentPadding = PaddingValues(AppTheme.dimensions.small)
            ) {
                item {
                    OrganizationPresentation(
                        name = state()::organizationName,
                        address = state()::organizationAddress,
                        imageUrl = state()::imageUrl,
                        onMapClick = onMapClick
                    )
                }
                items(state().audits) {
                    OrganizationCheckCard(
                        name = it::name,
                        lastTime = it::lastTime,
                        onClick = { auditDetails(it) }
                    )
                }
            }
        }
    }
}

@Preview
@Composable
private fun OrganizationAuditsScreenPreview() {
    AppTheme {
    }
}