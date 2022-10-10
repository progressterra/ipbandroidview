package com.progressterra.ipbandroidview.ui.audits

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.progressterra.ipbandroidview.R
import com.progressterra.ipbandroidview.composable.AuditCard
import com.progressterra.ipbandroidview.composable.CategoryDivider
import com.progressterra.ipbandroidview.composable.ThemedTopAppBar
import com.progressterra.ipbandroidview.theme.AppTheme

@Composable
fun AuditsScreen(state: AuditsState, interactor: AuditsInteractor) {
    Scaffold(topBar = {
        ThemedTopAppBar(title = stringResource(id = R.string.audits))
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
                verticalArrangement = Arrangement.spacedBy(8.dp)
            ) {
                items(state.documents.filter { it.done }) {
                    AuditCard(
                        modifier = Modifier.fillMaxWidth(),
                        name = it.name,
                        address = it.address,
                        percentage = it.percentage,
                        done = it.done,
                        onClick = { interactor.onDocumentDetails(it.id) }
                    )
                }
                item {
                    CategoryDivider(
                        modifier = Modifier.fillMaxWidth(),
                        title = stringResource(id = R.string.completed_audits)
                    )
                }
                items(state.documents.filter { !it.done }) {
                    AuditCard(
                        modifier = Modifier.fillMaxWidth(),
                        name = it.name,
                        address = it.address,
                        percentage = it.percentage,
                        done = it.done,
                        onClick = { interactor.onDocumentDetails(it.id) }
                    )
                }
            }
        }
    }
}

@Preview
@Composable
private fun AuditsScreenPreview() {
    AppTheme {
        AuditsScreen(
            state = AuditsState(
                listOf(
                    Document("", "Some audit 1", false, "Lenina 13", 87),
                    Document("", "Some audit 2", false, "Lenina 13", 15),
                    Document("", "Some audit 3", true, "Lenina 13", 30),
                    Document("", "Some audit 4", true, "Lenina 13", 90)
                )
            ), interactor = AuditsInteractor.Empty()
        )
    }
}