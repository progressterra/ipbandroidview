package com.progressterra.ipbandroidview.ui.organizations

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import com.progressterra.ipbandroidview.R
import com.progressterra.ipbandroidview.composable.OrganizationCard
import com.progressterra.ipbandroidview.composable.ThemedProgressBar
import com.progressterra.ipbandroidview.composable.ThemedRefreshButton
import com.progressterra.ipbandroidview.composable.ThemedTopAppBar
import com.progressterra.ipbandroidview.core.ScreenState
import com.progressterra.ipbandroidview.theme.AppTheme

@Composable
fun OrganizationsScreen(
    state: OrganizationsState, interactor: OrganizationsInteractor
) {
    Scaffold(topBar = {
        ThemedTopAppBar(title = stringResource(id = R.string.organizations))
    }) {
        Box(
            modifier = Modifier
                .fillMaxSize()
                .background(AppTheme.colors.background),
            contentAlignment = Alignment.Center
        ) {
            when (state.screenState) {
                ScreenState.SUCCESS -> Column(
                    modifier = Modifier
                        .fillMaxSize()
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
                                warnings = it.warnings,
                                onClick = {
                                    interactor.onOrganization(
                                        it
                                    )
                                })
                        }
                    }
                }
                ScreenState.ERROR -> ThemedRefreshButton(onClick = { interactor.onRefresh() })
                ScreenState.LOADING -> ThemedProgressBar()
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