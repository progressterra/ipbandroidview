package com.progressterra.ipbandroidview.ui.audits

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
import androidx.compose.ui.unit.dp
import androidx.compose.ui.zIndex
import androidx.constraintlayout.compose.ConstraintLayout
import androidx.constraintlayout.compose.Dimension
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
        Box(
            modifier = Modifier
                .fillMaxSize()
                .background(AppTheme.colors.background),
            contentAlignment = Alignment.Center
        ) {
            when (state.screenState) {
                ScreenState.SUCCESS ->
                    ConstraintLayout(
                        modifier = Modifier
                            .fillMaxSize()
                            .padding(
                                top = 8.dp,
                                start = 8.dp,
                                end = 8.dp
                            )
                    ) {
                        val (list, button) = createRefs()
                        LazyColumn(
                            modifier = Modifier.constrainAs(list) {
                                width = Dimension.matchParent
                                height = Dimension.matchParent
                            },
                            verticalArrangement = Arrangement.spacedBy(8.dp)
                        ) {
                            items(state.documents.filter { !it.done }) {
                                DocumentCard(
                                    modifier = Modifier.fillMaxWidth(),
                                    name = it.name,
                                    address = it.address,
                                    done = it.done,
                                    onClick = { interactor.onDocumentChecklist(it) },
                                    stats = it.stats
                                )
                            }
                            state.documents.filter { it.done }
                                .groupBy { it.finishDate }
                                .toSortedMap()
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
                                            done = document.done,
                                            onClick = { interactor.onDocumentChecklist(document) },
                                            stats = document.stats
                                        )
                                    }
                                }
                        }
                        ThemedButton(
                            modifier = Modifier
                                .zIndex(1f)
                                .constrainAs(button) {
                                    width = Dimension.fillToConstraints
                                    start.linkTo(parent.start, 16.dp)
                                    end.linkTo(parent.end, 16.dp)
                                    bottom.linkTo(parent.bottom, 24.dp)
                                },
                            onClick = { interactor.onAudit() },
                            text = stringResource(id = R.string.create_audit)
                        )
                    }
                ScreenState.ERROR -> ThemedRefreshButton(onClick = { interactor.onRefresh() })
                ScreenState.LOADING -> ThemedProgressBar()
            }
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
                        "",
                        "",
                        "",
                        ChecklistStats(7, 3, 3, 1),
                        false
                    ),
                    Document(
                        documentId = "",
                        "",
                        "",
                        "Some audit 2",
                        "Lenina 13",
                        25,
                        "",
                        "",
                        "",
                        ChecklistStats(7, 3, 3, 1),
                        false
                    ),
                    Document(
                        documentId = "",
                        "",
                        "",
                        "Some audit 3",
                        "Lenina 13",
                        25,
                        "",
                        "",
                        "",
                        ChecklistStats(7, 3, 3, 1),
                        false
                    ),
                    Document(
                        documentId = "",
                        "",
                        "",
                        "Some audit 4",
                        "Lenina 13",
                        25,
                        "",
                        "",
                        "",
                        ChecklistStats(7, 3, 3, 1),
                        false
                    )
                )
            ), interactor = DocumentsInteractor.Empty()
        )
    }
}