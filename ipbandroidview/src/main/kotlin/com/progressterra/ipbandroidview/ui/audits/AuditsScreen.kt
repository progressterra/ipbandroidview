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
import com.progressterra.ipbandroidview.core.ScreenState
import com.progressterra.ipbandroidview.theme.AppTheme

@Composable
fun AuditsScreen(state: AuditsState, interactor: AuditsInteractor) {
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
                                AuditCard(
                                    modifier = Modifier.fillMaxWidth(),
                                    name = it.name,
                                    address = it.address,
                                    percentage = it.percentage,
                                    done = it.done,
                                    onClick = { interactor.onDocumentChecklist(it) }
                                )
                            }
                            val doneDocuments = state.documents.filter { it.done }
                            if (doneDocuments.isNotEmpty()) {
                                item {
                                    CategoryDivider(
                                        modifier = Modifier.fillMaxWidth(),
                                        title = stringResource(id = R.string.completed_audits)
                                    )
                                }
                            }
                            items(doneDocuments) {
                                AuditCard(
                                    modifier = Modifier.fillMaxWidth(),
                                    name = it.name,
                                    address = it.address,
                                    percentage = it.percentage,
                                    done = it.done,
                                    onClick = { interactor.onDocumentChecklist(it) }
                                )
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
private fun AuditsScreenPreview() {
    AppTheme {
        AuditsScreen(
            state = AuditsState(
                screenState = ScreenState.SUCCESS,
                documents = listOf(
                    Document("", "Some audit 1", "Lenina 13", 87, 25, "", "", false),
                    Document("", "Some audit 2", "Lenina 13", 15, 25, "", "", false),
                    Document("", "Some audit 3", "Lenina 13", 30, 25, "", "", false),
                    Document("", "Some audit 4", "Lenina 13", 90, 25, "", "", false),
                    Document("", "Some audit 5", "Lenina 13", 87, 25, "", "", true),
                    Document("", "Some audit 6", "Lenina 13", 6, 25, "", "", true),
                    Document("", "Some audit 7", "Lenina 13", 56, 25, "", "", true),
                    Document("", "Some audit 8", "Lenina 13", 99, 25, "", "", true)
                )
            ), interactor = AuditsInteractor.Empty()
        )
    }
}