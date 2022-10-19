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
import com.progressterra.ipbandroidview.composable.StateBox
import com.progressterra.ipbandroidview.composable.OrganizationCheckCard
import com.progressterra.ipbandroidview.composable.OrganizationPresentation
import com.progressterra.ipbandroidview.composable.ThemedTopAppBar
import com.progressterra.ipbandroidview.core.ScreenState
import com.progressterra.ipbandroidview.theme.AppTheme

@Composable
fun OrganizationAuditsScreen(
    state: OrganizationAuditsState, interactor: OrganizationAuditsInteractor
) {
    Scaffold(topBar = {
        ThemedTopAppBar(
            onBack = { interactor.onBack() }, title = stringResource(id = R.string.audits)
        )
    }) {
        StateBox(
            modifier = Modifier
                .fillMaxSize()
                .background(AppTheme.colors.background),
            onRefresh = { interactor.refresh() },
            state = state.screenState
        ) {
            LazyColumn(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(top = 8.dp, start = 8.dp, end = 8.dp),
                verticalArrangement = Arrangement.spacedBy(8.dp)
            ) {
                item {
                    OrganizationPresentation(name = state.organizationName,
                        address = state.organizationAddress,
                        imageUrl = state.imageUrl,
                        onMapClick = { interactor.onMapClick() })
                }
                items(state.audits) {
                    OrganizationCheckCard(
                        name = it.name,
                        lastTime = it.lastTime,
                        warning = it.warning,
                        onClick = {
                            interactor.onAuditClick(it)
                        }
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
        OrganizationAuditsScreen(
            state = OrganizationAuditsState(
                screenState = ScreenState.SUCCESS,
                organizationName = "Velvet software company",
                organizationAddress = "Lenina 13",
                imageUrl = "",
                latitude = 0.0,
                longitude = 0.0,
                audits = listOf(
                    OrganizationAudit(
                        "", "Check some 1", "Yesterday", false
                    ), OrganizationAudit(
                        "", "Check some 2", " Yesterday ", false
                    ), OrganizationAudit(
                        "", "Check some 3", " Yesterday ", false
                    ), OrganizationAudit(
                        "", "Check some 4", " Yesterday ", true
                    ), OrganizationAudit(
                        "", "Check some 5", " Yesterday ", false
                    ), OrganizationAudit(
                        "", "Check some 6", " Yesterday ", false
                    ), OrganizationAudit(
                        "", "Check some 7", " Yesterday ", true
                    )
                )
            ), interactor = OrganizationAuditsInteractor.Empty()
        )
    }
}

@Preview
@Composable
private fun OrganizationAuditsScreenPreviewError() {
    AppTheme {
        OrganizationAuditsScreen(
            state = OrganizationAuditsState(
                organizationName = "Velvet software company",
                organizationAddress = "Lenina 13",
                imageUrl = "",
                latitude = 0.0,
                longitude = 0.0,
                screenState = ScreenState.ERROR
            ), interactor = OrganizationAuditsInteractor.Empty()
        )
    }
}

@Preview
@Composable
private fun OrganizationAuditsScreenPreviewLoading() {
    AppTheme {
        OrganizationAuditsScreen(
            state = OrganizationAuditsState(
                organizationName = "Velvet software company",
                organizationAddress = "Lenina 13",
                imageUrl = "",
                latitude = 0.0,
                longitude = 0.0,
                screenState = ScreenState.LOADING
            ), interactor = OrganizationAuditsInteractor.Empty()
        )
    }
}

