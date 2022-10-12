package com.progressterra.ipbandroidview.ui.auditchecks

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.progressterra.ipbandroidview.R
import com.progressterra.ipbandroidview.composable.*
import com.progressterra.ipbandroidview.core.ScreenState
import com.progressterra.ipbandroidview.theme.AppTheme

@Composable
fun AuditChecksScreen(state: AuditChecksState, interactor: AuditChecksInteractor) {
    Scaffold(topBar = {
        ThemedTopAppBar(onBack = { interactor.onBack() },
            title = stringResource(id = R.string.audit),
            actions = {
                if (!state.done) {
                    if (state.ongoing) {
                        IconButton(onClick = { interactor.onStop() }) {
                            Icon(
                                painter = painterResource(id = R.drawable.ic_pause),
                                contentDescription = stringResource(
                                    id = R.string.pause_audit
                                ),
                                tint = AppTheme.colors.gray1
                            )
                        }
                    } else {
                        IconButton(onClick = { interactor.onStart() }) {
                            Icon(
                                painter = painterResource(id = R.drawable.ic_play),
                                contentDescription = stringResource(
                                    id = R.string.start_audit
                                ),
                                tint = AppTheme.colors.gray1
                            )
                        }
                    }
                }
            }
        )
    }) {
        Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
            when (state.screenState) {
                ScreenState.SUCCESS -> LazyColumn(
                    modifier = Modifier
                        .fillMaxSize()
                        .background(AppTheme.colors.background)
                        .padding(
                            top = AppTheme.dimensions.small,
                            start = AppTheme.dimensions.small,
                            end = AppTheme.dimensions.small
                        ), verticalArrangement = Arrangement.spacedBy(8.dp)
                ) {
                    item {
                        AuditTitle(
                            modifier = Modifier.fillMaxWidth(),
                            name = state.name,
                            repetitiveness = state.repetitiveness,
                            lastTimeChecked = state.lastTimeChecked,
                            checkCounter = state.checkCounter
                        )
                    }
                    state.checks.groupBy { it.category }.forEach { (category, checks) ->
                        item {
                            CategoryDivider(
                                modifier = Modifier.fillMaxWidth(), title = category
                            )
                        }
                        items(checks) {
                            CheckCard(
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .height(IntrinsicSize.Max),
                                onClick = { interactor.onCheck(it) },
                                name = it.name,
                                state = it.state
                            )
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
private fun AuditChecksScreenPreview() {
    AppTheme {
        AuditChecksScreen(
            state = AuditChecksState(
                screenState = ScreenState.SUCCESS,
                ongoing = true,
                name = "Some audit",
                checkCounter = 25,
                repetitiveness = "Every day",
                lastTimeChecked = "yesterday",
                checks = listOf(
                    Check(
                        CheckState.FAILED,
                        "",
                        "1 category",
                        "Some check 1\nWith more text",
                        ""
                    ),
                    Check(CheckState.SUCCESSFUL, "", "1 category", "Some check 2", ""),
                    Check(CheckState.FAILED, "", "1 category", "Some check 3", ""),
                    Check(CheckState.FAILED, "", "1 category", "Some check 4", ""),
                    Check(
                        CheckState.ONGOING,
                        "",
                        "1 category",
                        "Some check 5\nWith more text",
                        ""
                    ),
                    Check(CheckState.ONGOING, "", "2 category", "Some check 6", ""),
                    Check(CheckState.ONGOING, "", "2 category", "Some check 7", ""),
                    Check(
                        CheckState.SUCCESSFUL,
                        "",
                        "2 category",
                        "Some check 8\nWith more text",
                        ""
                    ),
                    Check(CheckState.SUCCESSFUL, "", "3 category", "Some check 9", "")
                ),
            ), interactor = AuditChecksInteractor.Empty()
        )
    }
}

@Preview
@Composable
private fun AuditChecksScreenPreviewNone() {
    AppTheme {
        AuditChecksScreen(
            state = AuditChecksState(
                screenState = ScreenState.SUCCESS,
                name = "Some audit",
                checkCounter = 25,
                repetitiveness = "Every day",
                lastTimeChecked = "yesterday",
                checks = listOf(
                    Check(
                        CheckState.NONE,
                        "",
                        "1 category",
                        "Some check 1\nWith more text",
                        ""
                    ),
                    Check(CheckState.NONE, "", "1 category", "Some check 2", ""),
                    Check(CheckState.NONE, "", "1 category", "Some check 3", ""),
                    Check(CheckState.NONE, "", "1 category", "Some check 4", ""),
                    Check(
                        CheckState.NONE,
                        "",
                        "1 category",
                        "Some check 5\nWith more text",
                        ""
                    ),
                    Check(CheckState.NONE, "", "2 category", "Some check 6", ""),
                    Check(CheckState.NONE, "", "2 category", "Some check 7", ""),
                    Check(
                        CheckState.NONE,
                        "",
                        "2 category",
                        "Some check 8\nWith more text",
                        ""
                    ),
                    Check(CheckState.NONE, "", "3 category", "Some check 9", "")
                ),
            ), interactor = AuditChecksInteractor.Empty()
        )
    }
}

@Preview
@Composable
private fun AuditChecksScreenPreviewLoading() {
    AppTheme {
        AuditChecksScreen(
            state = AuditChecksState(
                screenState = ScreenState.LOADING,
                ongoing = false,
                name = "Some audit",
                checkCounter = 25,
                repetitiveness = "Every day",
                lastTimeChecked = "yesterday",
                checks = listOf(),
            ), interactor = AuditChecksInteractor.Empty()
        )
    }
}

@Preview
@Composable
private fun AuditChecksScreenPreviewError() {
    AppTheme {
        AuditChecksScreen(
            state = AuditChecksState(
                screenState = ScreenState.ERROR,
                ongoing = false,
                name = "Some audit",
                checkCounter = 25,
                repetitiveness = "Every day",
                lastTimeChecked = "yesterday",
                checks = listOf(),
            ), interactor = AuditChecksInteractor.Empty()
        )
    }
}