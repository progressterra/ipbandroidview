package com.progressterra.ipbandroidview.ui.organizations

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import com.progressterra.ipbandroidview.R
import com.progressterra.ipbandroidview.composable.OrganizationCard
import com.progressterra.ipbandroidview.composable.ThemedTopAppBar
import com.progressterra.ipbandroidview.theme.AppTheme

@Composable
fun OrganizationsScreen(
    state: OrganizationsState, interactor: OrganizationsInteractor
) {
    Scaffold(topBar = {
        ThemedTopAppBar(title = stringResource(id = R.string.organizations))
    }) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .background(AppTheme.colors.background)
                .padding(
                    top = AppTheme.dimensions.small,
                    start = AppTheme.dimensions.small,
                    end = AppTheme.dimensions.small
                )
        ) {
            LazyColumn(
                modifier = Modifier.fillMaxSize(),
                verticalArrangement = Arrangement.spacedBy(AppTheme.dimensions.small)
            ) {
                items(state.organizations) {
                    OrganizationCard(modifier = Modifier.fillMaxWidth(),
                        address = it.address,
                        description = it.name,
                        availableChecks = it.availableChecks,
                        onClick = {
                            interactor.onOrganization(
                                it
                            )
                        })
                }
            }
        }
    }
}

@Preview
@Composable
private fun AuditsScreenPreview() {
    AppTheme {
        OrganizationsScreen(
            state = OrganizationsState(
                listOf(
                    Organization(
                        "пл Дружбы народов, 45", "", 0, "«Кофемания»", "", 0.0, 0.0
                    ), Organization(
                        "пл Дружбы народов, 45", "", 1, "«KFC»", "", 0.0, 0.0
                    ), Organization(
                        "пл Дружбы народов, 45", "", 2, "«Кофемания»", "", 0.0, 0.0
                    )
                )
            ), interactor = OrganizationsInteractor.Empty()
        )
    }
}