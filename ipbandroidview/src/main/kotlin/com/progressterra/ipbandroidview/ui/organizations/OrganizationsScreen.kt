package com.progressterra.ipbandroidview.ui.organizations

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.progressterra.ipbandroidview.composable.OrganizationCard
import com.progressterra.ipbandroidview.theme.AppTheme

@Composable
fun OrganizationsScreen(
    state: OrganizationsState, interactor: OrganizationsInteractor
) {
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
                    description = it.description,
                    availableChecks = it.availableChecks,
                    onClick = { interactor.onOrganization(it.id) })
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
                        "пл Дружбы народов, 45", "", 0, "«Кофемания»"
                    ), Organization(
                        "пл Дружбы народов, 45", "", 1, "«KFC»"
                    ), Organization(
                        "пл Дружбы народов, 45", "", 2, "«Кофемания»"
                    )
                )
            ), interactor = OrganizationsInteractor.Empty()
        )
    }
}