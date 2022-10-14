package com.progressterra.ipbandroidview.ui.checklist

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.progressterra.ipbandroidview.R
import com.progressterra.ipbandroidview.composable.*
import com.progressterra.ipbandroidview.composable.yesno.YesNo
import com.progressterra.ipbandroidview.composable.yesno.YesNoButton
import com.progressterra.ipbandroidview.core.ScreenState
import com.progressterra.ipbandroidview.theme.AppTheme

@OptIn(ExperimentalMaterialApi::class)
@Composable
fun ChecklistScreen(state: ChecklistState, interactor: ChecklistInteractor) {

    val sheetState = rememberModalBottomSheetState(
        initialValue = ModalBottomSheetValue.Hidden,
        skipHalfExpanded = true,
        confirmStateChange = {
            interactor.onSheetVisibilityChange(it == ModalBottomSheetValue.Expanded)
            true
        })
    LaunchedEffect(state.sheetVisibility) {
        if (state.sheetVisibility && !sheetState.isVisible) sheetState.show()
        if (!state.sheetVisibility && sheetState.isVisible) sheetState.hide()
    }

    ModalBottomSheetLayout(
        modifier = Modifier.padding(
            top = 8.dp, start = 8.dp, end = 8.dp
        ), sheetState = sheetState, sheetShape = RoundedCornerShape(
            topStart = 8.dp, topEnd = 8.dp
        ), sheetContent = {
            state.currentCheck?.let {
                ThemedTopDialogBar(title = "PLACEHOLDER", rightActions = {
                    IconButton(
                        modifier = Modifier.size(24.dp),
                        onClick = { interactor.closeSheet() }) {
                        Icon(
                            painter = painterResource(id = R.drawable.ic_mark),
                            contentDescription = stringResource(R.string.close),
                            tint = AppTheme.colors.gray1
                        )
                    }
                })
                Column(
                    modifier = Modifier
                        .clip(RoundedCornerShape(AppTheme.dimensions.mediumRounding))
                        .background(AppTheme.colors.surfaces)
                        .padding(12.dp)
                ) {
                    Text(
                        text = it.description,
                        color = AppTheme.colors.black,
                        style = AppTheme.typography.text
                    )
                }
                Spacer(modifier = Modifier.size(8.dp))
                Column(
                    modifier = Modifier
                        .clip(RoundedCornerShape(AppTheme.dimensions.mediumRounding))
                        .background(AppTheme.colors.surfaces)
                        .padding(12.dp),
                    verticalArrangement = Arrangement.spacedBy(12.dp)
                ) {
                    YesNoButton(modifier = Modifier.fillMaxWidth(),
                        state = it.state,
                        onClick = { interactor.yesNo(it) })
                    ThemedNotebook(
                        modifier = Modifier.fillMaxWidth(),
                        text = it.comment,
                        hint = stringResource(
                            id = R.string.text_comment
                        ),
                        onChange = { interactor.onCheckCommentaryChange(it) },
                        enabled = !it.state.done
                    )
                }
            }
        }, sheetBackgroundColor = AppTheme.colors.background
    ) {
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
                })
        }) {
            Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
                when (state.screenState) {
                    ScreenState.SUCCESS -> LazyColumn(
                        modifier = Modifier
                            .fillMaxSize()
                            .background(AppTheme.colors.background)
                            .padding(
                                top = 8.dp, start = 8.dp, end = 8.dp
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
}

@Preview
@Composable
private fun ChecklistScreenPreview() {
    AppTheme {
        ChecklistScreen(
            state = ChecklistState(
                screenState = ScreenState.SUCCESS,
                ongoing = true,
                name = "Some audit",
                checkCounter = 25,
                repetitiveness = "Every day",
                lastTimeChecked = "yesterday",
                checks = listOf(
                    Check(
                        CheckState(false, YesNo.YES),
                        "",
                        "1 category",
                        "Some check 1\nWith more text",
                        "",
                        ""
                    ),
                    Check(CheckState(false, YesNo.YES), "", "1 category", "Some check 2", "", ""),
                    Check(CheckState(false, YesNo.YES), "", "1 category", "Some check 3", "", ""),
                    Check(CheckState(false, YesNo.YES), "", "1 category", "Some check 4", "", ""),
                    Check(
                        CheckState(false, YesNo.YES),
                        "",
                        "1 category",
                        "Some check 5\nWith more text",
                        "",
                        ""
                    ),
                    Check(CheckState(false, YesNo.YES), "", "2 category", "Some check 6", "", ""),
                    Check(CheckState(false, YesNo.YES), "", "2 category", "Some check 7", "", ""),
                    Check(
                        CheckState(false, YesNo.YES),
                        "",
                        "2 category",
                        "Some check 8\nWith more text",
                        "",
                        ""
                    ),
                    Check(CheckState(false, YesNo.YES), "", "3 category", "Some check 9", "", "")
                ),
            ), interactor = ChecklistInteractor.Empty()
        )
    }
}

@Preview
@Composable
private fun ChecklistScreenPreviewLoading() {
    AppTheme {
        ChecklistScreen(
            state = ChecklistState(
                screenState = ScreenState.LOADING,
                ongoing = false,
                name = "Some audit",
                checkCounter = 25,
                repetitiveness = "Every day",
                lastTimeChecked = "yesterday",
                checks = listOf(),
            ), interactor = ChecklistInteractor.Empty()
        )
    }
}

@Preview
@Composable
private fun ChecklistScreenPreviewError() {
    AppTheme {
        ChecklistScreen(
            state = ChecklistState(
                screenState = ScreenState.ERROR,
                ongoing = false,
                name = "Some audit",
                checkCounter = 25,
                repetitiveness = "Every day",
                lastTimeChecked = "yesterday",
                checks = listOf(),
            ), interactor = ChecklistInteractor.Empty()
        )
    }
}

@Preview
@Composable
private fun ChecklistScreenPreviewDialog() {
    AppTheme {
        ChecklistScreen(
            state = ChecklistState(
                screenState = ScreenState.SUCCESS,
                ongoing = false,
                name = "Some audit",
                checkCounter = 25,
                repetitiveness = "Every day",
                lastTimeChecked = "yesterday",
                checks = listOf(),
                sheetVisibility = true,
                currentCheck = Check(
                    CheckState(false, YesNo.YES),
                    "",
                    "2 category",
                    "Some check 8\nWith more text",
                    "",
                    "description"
                )
            ), interactor = ChecklistInteractor.Empty()
        )
    }
}