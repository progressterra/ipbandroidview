package com.progressterra.ipbandroidview.ui.documents

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.Scaffold
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.onGloballyPositioned
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.zIndex
import com.progressterra.ipbandroidview.R
import com.progressterra.ipbandroidview.composable.*
import com.progressterra.ipbandroidview.composable.stats.ChecklistStats
import com.progressterra.ipbandroidview.core.ScreenState
import com.progressterra.ipbandroidview.theme.AppTheme

@Composable
fun DocumentsScreen(state: DocumentsState, interactor: DocumentsInteractor) {
    Scaffold(topBar = {
        ThemedTopAppBar(title = stringResource(id = R.string.audits))
    }) {
        StateBox(
            modifier = Modifier
                .fillMaxSize()
                .background(AppTheme.colors.background),
            state = state.screenState,
            onRefresh = { interactor.refresh() }
        ) {
            var spacerSize by remember { mutableStateOf(0.dp) }
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
                items(state.documents.filter { it.finishDate == null }) {
                    DocumentCard(
                        modifier = Modifier.fillMaxWidth(),
                        name = it.name,
                        address = it.address,
                        onClick = { interactor.onDocumentChecklist(it) },
                        stats = it.stats,
                        backgroundColor = Color(0xFFFFCA61)
                    )
                }
                state.documents.filter { it.finishDate != null }
                    .groupBy { it.finishDate!! }
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
                                onClick = { interactor.onDocumentChecklist(document) },
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
                onClick = { interactor.onAudit() },
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
                screenState = ScreenState.SUCCESS,
                documents = listOf(
                    Document(
                        documentId = "",
                        "",
                        "",
                        "Some audit 1",
                        "Lenina 13",
                        25,
                        null,
                        ChecklistStats(7, 3, 3, 1)
                    ),
                    Document(
                        documentId = "",
                        "",
                        "",
                        "Some audit 2",
                        "Lenina 13",
                        25,
                        null,
                        ChecklistStats(7, 3, 3, 1)
                    ),
                    Document(
                        documentId = "",
                        "",
                        "",
                        "Some audit 3",
                        "Lenina 13",
                        25,
                        null,
                        ChecklistStats(7, 3, 3, 1)
                    ),
                    Document(
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