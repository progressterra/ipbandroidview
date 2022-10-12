package com.progressterra.ipbandroidview.ui.auditchecks

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.progressterra.ipbandroidview.R
import com.progressterra.ipbandroidview.composable.AuditTitle
import com.progressterra.ipbandroidview.composable.CategoryDivider
import com.progressterra.ipbandroidview.composable.CheckCard
import com.progressterra.ipbandroidview.composable.ThemedTopAppBar
import com.progressterra.ipbandroidview.theme.AppTheme

@Composable
fun AuditChecksScreen(state: AuditChecksState, interactor: AuditChecksInteractor) {
    Scaffold(topBar = {
        ThemedTopAppBar(
            onBack = { interactor.onBack() },
            title = stringResource(id = R.string.audit),
            actions = {
                when (state.state) {
                    AuditState.NONE -> IconButton(
                        onClick = { interactor.onStart() }
                    ) {
                        Icon(
                            painter = painterResource(id = R.drawable.ic_play),
                            contentDescription = stringResource(
                                id = R.string.start_audit
                            ),
                            tint = AppTheme.colors.gray1
                        )
                    }
                    AuditState.ONGOING -> IconButton(
                        onClick = { interactor.onStop() }
                    ) {
                        Icon(
                            painter = painterResource(id = R.drawable.ic_pause),
                            contentDescription = stringResource(
                                id = R.string.pause_audit
                            ),
                            tint = AppTheme.colors.gray1
                        )
                    }
                    AuditState.FINISHED -> {}
                }
            }
        )
    }) {
        LazyColumn(
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
    }
}

@Preview
@Composable
private fun AuditChecksScreenPreview() {
    AppTheme {
        AuditChecksScreen(
            state = AuditChecksState(
                state = AuditState.ONGOING,
                name = "Some audit",
                checkCounter = 25,
                repetitiveness = "Every day",
                lastTimeChecked = "yesterday",
                checks = listOf(
                    Check(CheckState.FAILED, "", "1 category", "Some check 1\nWith more text", ""),
                    Check(CheckState.SUCCESSFUL, "", "1 category", "Some check 2", ""),
                    Check(CheckState.FAILED, "", "1 category", "Some check 3", ""),
                    Check(CheckState.FAILED, "", "1 category", "Some check 4", ""),
                    Check(
                        CheckState.ONGOING, "", "1 category", "Some check 5\nWith more text", ""
                    ),
                    Check(CheckState.ONGOING, "", "2 category", "Some check 6", ""),
                    Check(CheckState.ONGOING, "", "2 category", "Some check 7", ""),
                    Check(
                        CheckState.SUCCESSFUL, "", "2 category", "Some check 8\nWith more text", ""
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
                state = AuditState.NONE,
                name = "Some audit",
                checkCounter = 25,
                repetitiveness = "Every day",
                lastTimeChecked = "yesterday",
                checks = listOf(
                    Check(CheckState.NONE, "", "1 category", "Some check 1\nWith more text", ""),
                    Check(CheckState.NONE, "", "1 category", "Some check 2", ""),
                    Check(CheckState.NONE, "", "1 category", "Some check 3", ""),
                    Check(CheckState.NONE, "", "1 category", "Some check 4", ""),
                    Check(
                        CheckState.NONE, "", "1 category", "Some check 5\nWith more text", ""
                    ),
                    Check(CheckState.NONE, "", "2 category", "Some check 6", ""),
                    Check(CheckState.NONE, "", "2 category", "Some check 7", ""),
                    Check(
                        CheckState.NONE, "", "2 category", "Some check 8\nWith more text", ""
                    ),
                    Check(CheckState.NONE, "", "3 category", "Some check 9", "")
                ),
            ), interactor = AuditChecksInteractor.Empty()
        )
    }
}

@Preview
@Composable
private fun AuditChecksScreenPreviewFinished() {
    AppTheme {
        AuditChecksScreen(
            state = AuditChecksState(
                state = AuditState.FINISHED,
                name = "Some audit",
                checkCounter = 25,
                repetitiveness = "Every day",
                lastTimeChecked = "yesterday",
                checks = listOf(
                    Check(CheckState.NONE, "", "1 category", "Some check 1\nWith more text", ""),
                    Check(CheckState.NONE, "", "1 category", "Some check 2", ""),
                    Check(CheckState.NONE, "", "1 category", "Some check 3", ""),
                    Check(CheckState.NONE, "", "1 category", "Some check 4", ""),
                    Check(
                        CheckState.NONE, "", "1 category", "Some check 5\nWith more text", ""
                    ),
                    Check(CheckState.NONE, "", "2 category", "Some check 6", ""),
                    Check(CheckState.NONE, "", "2 category", "Some check 7", ""),
                    Check(
                        CheckState.NONE, "", "2 category", "Some check 8\nWith more text", ""
                    ),
                    Check(CheckState.NONE, "", "3 category", "Some check 9", "")
                ),
            ), interactor = AuditChecksInteractor.Empty()
        )
    }
}