package com.progressterra.ipbandroidview.ui.documents

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.onGloballyPositioned
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.zIndex
import com.progressterra.ipbandroidview.R
import com.progressterra.ipbandroidview.components.CategoryDivider
import com.progressterra.ipbandroidview.components.DocumentCard
import com.progressterra.ipbandroidview.components.StateBox
import com.progressterra.ipbandroidview.components.ThemedButton
import com.progressterra.ipbandroidview.components.stats.ChecklistStats
import com.progressterra.ipbandroidview.components.topbar.ThemedTopAppBar
import com.progressterra.ipbandroidview.core.ScreenState
import com.progressterra.ipbandroidview.theme.AppTheme

@Composable
fun DocumentsScreen(state: DocumentsState, interactor: DocumentsInteractor) {
    Scaffold(topBar = {
        ThemedTopAppBar(title = stringResource(id = R.string.audits))
    }) { padding ->
        StateBox(
            modifier = Modifier
                .fillMaxSize()
                .background(AppTheme.colors.background)
                .padding(padding),
            state = state.screenState,
            onRefresh = { interactor.refresh() }) {
            var spacerSize by remember { mutableStateOf(0.dp) }
            val unfinishedDocs by remember(state.documents) {
                mutableStateOf(state.documents.filter { it.finishDate == null })
            }
            val finishedGroupedDocs by remember(state.documents) {
                mutableStateOf(state.documents.filter { it.finishDate != null }
                    .groupBy { it.finishDate!! })
            }
            LazyColumn(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(
                        top = 8.dp, start = 8.dp, end = 8.dp
                    ), verticalArrangement = Arrangement.spacedBy(8.dp)
            ) {

                items(unfinishedDocs) {
                    DocumentCard(
                        modifier = Modifier.fillMaxWidth(),
                        name = it.name,
                        address = it.address,
                        onClick = { interactor.openDetails(it) },
                        stats = it.stats,
                        backgroundColor = AppTheme.colors.secondary
                    )
                }
                finishedGroupedDocs
                    .forEach {
                        item {
                            CategoryDivider(
                                modifier = Modifier.fillMaxWidth(),
                                title = "${stringResource(id = R.string.completed_audits)} ${it.key}"
                            )
                        }
                        items(it.value) { document ->
                            DocumentCard(
                                modifier = Modifier.fillMaxWidth(),
                                name = document.name,
                                address = document.address,
                                onClick = { interactor.openDetails(document) },
                                stats = document.stats
                            )
                        }
                    }
                item {
                    Spacer(modifier = Modifier.size(spacerSize + 24.dp))
                }
            }
            val density = LocalDensity.current
            ThemedButton(
                modifier = Modifier
                    .zIndex(1f)
                    .align(Alignment.BottomCenter)
                    .padding(start = 24.dp, end = 24.dp, bottom = 24.dp)
                    .fillMaxWidth()
                    .onGloballyPositioned {
                        spacerSize = with(density) { it.size.height.toDp() }
                    },
                onClick = { interactor.openOrganizations() },
                text = stringResource(id = R.string.create_audit)
            )
        }
    }
}

@Preview
@Composable
private fun DocumentsScreenPreview() {
    AppTheme {
        DocumentsScreen(
            state = DocumentsState(
                screenState = ScreenState.SUCCESS, documents = listOf(
                    Document(
                        documentId = "",
                        "",
                        "",
                        "Some audit 1",
                        "Lenina 13",
                        25,
                        null,
                        ChecklistStats(7, 3, 3, 1)
                    ), Document(
                        documentId = "",
                        "",
                        "",
                        "Some audit 2",
                        "Lenina 13",
                        25,
                        null,
                        ChecklistStats(7, 3, 3, 1)
                    ), Document(
                        documentId = "",
                        "",
                        "",
                        "Some audit 3",
                        "Lenina 13",
                        25,
                        null,
                        ChecklistStats(7, 3, 3, 1)
                    ), Document(
                        documentId = "",
                        "",
                        "",
                        "Some audit 4",
                        "Lenina 13",
                        25,
                        null,
                        ChecklistStats(7, 3, 3, 1)
                    )
                )
            ), interactor = DocumentsInteractor.Empty()
        )
    }
}