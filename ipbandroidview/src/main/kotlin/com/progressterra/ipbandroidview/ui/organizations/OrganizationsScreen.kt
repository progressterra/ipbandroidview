package com.progressterra.ipbandroidview.ui.organizations

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
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
import com.progressterra.ipbandroidview.components.StateBox
import com.progressterra.ipbandroidview.components.OrganizationCard
import com.progressterra.ipbandroidview.components.ThemedTopAppBar
import com.progressterra.ipbandroidview.core.ScreenState
import com.progressterra.ipbandroidview.theme.AppTheme

@Composable
fun OrganizationsScreen(
    state: OrganizationsState, interactor: OrganizationsInteractor
) {
    Scaffold(topBar = {
        ThemedTopAppBar(title = stringResource(id = R.string.organizations))
    }) {
        StateBox(
            modifier = Modifier
                .fillMaxSize()
                .background(AppTheme.colors.background),
            state = state.screenState,
            onRefresh = { interactor.refresh() }
        ) {
            LazyColumn(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(
                        top = 8.dp,
                        start = 8.dp,
                        end = 8.dp
                    ),
                verticalArrangement = Arrangement.spacedBy(8.dp)
            ) {
                items(state.organizations) {
                    OrganizationCard(modifier = Modifier.fillMaxWidth(),
                        address = it.address,
                        description = it.name,
                        warnings = it.warnings,
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
private fun AuditsScreenPreviewSuccess() {
    AppTheme {
        OrganizationsScreen(
            state = OrganizationsState(
                screenState = ScreenState.SUCCESS,
                organizations = listOf(
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

@Preview
@Composable
private fun AuditsScreenPreviewLoading() {
    AppTheme {
        OrganizationsScreen(
            state = OrganizationsState(
                screenState = ScreenState.LOADING,
                organizations = listOf()
            ), interactor = OrganizationsInteractor.Empty()
        )
    }
}

@Preview
@Composable
private fun AuditsScreenPreviewError() {
    AppTheme {
        OrganizationsScreen(
            state = OrganizationsState(
                screenState = ScreenState.ERROR,
                organizations = listOf()
            ), interactor = OrganizationsInteractor.Empty()
        )
    }
}