package com.progressterra.ipbandroidview.ui.checklist

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
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
import com.progressterra.ipbandroidview.core.Checklist
import com.progressterra.ipbandroidview.theme.AppTheme
import kotlinx.coroutines.launch

@OptIn(ExperimentalMaterialApi::class)
@Composable
fun ChecklistScreen(state: ChecklistState, interactor: ChecklistInteractor) {
    val sheetState = rememberModalBottomSheetState(
        initialValue = ModalBottomSheetValue.Hidden,
        skipHalfExpanded = true
    )
    val coroutineScope = rememberCoroutineScope()
    ModalBottomSheetLayout(
        sheetState = sheetState, sheetShape = RoundedCornerShape(
            topStart = 8.dp, topEnd = 8.dp
        ), sheetContent = {
            if (state.currentCheck == null) {
                Column(
                    modifier = Modifier
                        .background(AppTheme.colors.background)
                        .fillMaxWidth(),
                    verticalArrangement = Arrangement.Center,
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    ThemedTopDialogBar(rightActions = {
                        IconButton(
                            modifier = Modifier.size(24.dp),
                            onClick = { coroutineScope.launch { sheetState.hide() } }) {
                            Icon(
                                painter = painterResource(id = R.drawable.ic_mark),
                                contentDescription = stringResource(R.string.close),
                                tint = AppTheme.colors.gray1
                            )
                        }
                    })
                    Spacer(modifier = Modifier.size(150.dp))
                    ThemedProgressBar()
                    Spacer(modifier = Modifier.size(150.dp))
                }
            } else {
                ThemedTopDialogBar(title = "PLACEHOLDER", rightActions = {
                    IconButton(
                        modifier = Modifier.size(24.dp),
                        onClick = { coroutineScope.launch { sheetState.hide() } }) {
                        Icon(
                            painter = painterResource(id = R.drawable.ic_mark),
                            contentDescription = stringResource(R.string.close),
                            tint = AppTheme.colors.gray1
                        )
                    }
                })
                Column(
                    modifier = Modifier
                        .background(AppTheme.colors.background)
                        .padding(
                            top = 8.dp, start = 8.dp, end = 8.dp
                        )
                ) {
                    Column(
                        modifier = Modifier
                            .clip(RoundedCornerShape(AppTheme.dimensions.mediumRounding))
                            .background(AppTheme.colors.surfaces)
                            .padding(12.dp)
                            .fillMaxWidth()
                    ) {
                        Text(
                            text = state.currentCheck.description,
                            color = AppTheme.colors.black,
                            style = AppTheme.typography.text
                        )
                    }
                    Spacer(modifier = Modifier.size(8.dp))
                    Column(
                        modifier = Modifier
                            .clip(RoundedCornerShape(AppTheme.dimensions.mediumRounding))
                            .background(AppTheme.colors.surfaces)
                    ) {
                        Box(modifier = Modifier.padding(12.dp)) {
                            YesNoButton(
                                modifier = Modifier.fillMaxWidth(),
                                yesNo = state.currentCheck.yesNo,
                                onClick = { interactor.yesNo(it) },
                                enabled = state.checklist.ongoing
                            )
                        }
                        Box(modifier = Modifier.padding(horizontal = 12.dp)) {
                            ThemedNotebook(
                                modifier = Modifier.fillMaxWidth(),
                                text = state.currentCheck.comment,
                                hint = stringResource(
                                    id = R.string.text_comment
                                ),
                                onChange = { interactor.onCheckCommentaryChange(it) },
                                enabled = state.checklist.ongoing
                            )
                        }
                        Box(modifier = Modifier.padding(4.dp)) {
                            VoiceInput(
                                modifier = Modifier.fillMaxWidth(),
                                voiceState = state.voiceState,
                                onStartPausePlay = { interactor.startPauseVoicePlay() },
                                onStartStopRecording = { interactor.startStopVoiceRecording() },
                                onRemove = {
                                    interactor.removeRecord()
                                }, enabled = state.checklist.ongoing
                            )
                        }
                    }
                    if (state.checklist.ongoing) {
                        Spacer(modifier = Modifier.size(25.dp))
                        Row(Modifier.padding(horizontal = 8.dp)) {
                            ThemedButton(
                                modifier = Modifier.fillMaxWidth(),
                                onClick = {
                                    interactor.ready()
                                    coroutineScope.launch { sheetState.hide() }
                                },
                                text = stringResource(id = R.string.ready)
                            )
                        }
                    }
                    Spacer(modifier = Modifier.size(25.dp))
                }
            }
        }, sheetBackgroundColor = AppTheme.colors.surfaces
    ) {
        Scaffold(topBar = {
            ThemedTopAppBar(onBack = { interactor.back() },
                title = stringResource(id = R.string.audit),
                actions = {
                    if (!state.checklist.done) {
                        IconButton(onClick = { interactor.startStopAudit() }) {
                            Icon(
                                painter = painterResource(id = if (state.checklist.ongoing) R.drawable.ic_pause else R.drawable.ic_play),
                                contentDescription = stringResource(
                                    id = if (state.checklist.ongoing) R.string.pause_audit else R.string.start_audit
                                ),
                                tint = AppTheme.colors.gray1
                            )
                        }
                    }
                })
        }) {
            Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
                LazyColumn(
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
                            name = state.checklist.name,
                            repetitiveness = state.checklist.repetitiveness,
                            lastTimeChecked = state.checklist.lastTimeChecked,
                            checkCounter = state.checklist.checks.size
                        )
                    }
                    state.checklist.checks.groupBy { it.category }.forEach { (category, checks) ->
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
                                onClick = {
                                    interactor.onCheck(it)
                                    coroutineScope.launch { sheetState.show() }
                                },
                                name = it.name,
                                yesNo = it.yesNo
                            )
                        }
                    }

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
                checklist = Checklist(
                    ongoing = true,
                    name = "Some audit",
                    repetitiveness = "Every day",
                    lastTimeChecked = "yesterday",
                    checks = listOf(
                        Check(
                            yesNo = YesNo.YES,
                            "",
                            "1 category",
                            "Some check 1\nWith more text",
                            "",
                            ""
                        ),
                        Check(
                            yesNo = YesNo.YES,
                            "",
                            "1 category",
                            "Some check 2",
                            "",
                            ""
                        ),
                        Check(
                            yesNo = YesNo.YES,
                            "",
                            "1 category",
                            "Some check 3",
                            "",
                            ""
                        ),
                        Check(
                            yesNo = YesNo.YES,
                            "",
                            "1 category",
                            "Some check 4",
                            "",
                            ""
                        ),
                        Check(
                            yesNo = YesNo.YES,
                            "",
                            "1 category",
                            "Some check 5\nWith more text",
                            "",
                            ""
                        ),
                        Check(
                            yesNo = YesNo.YES,
                            "",
                            "2 category",
                            "Some check 6",
                            "",
                            ""
                        ),
                        Check(
                            yesNo = YesNo.YES,
                            "",
                            "2 category",
                            "Some check 7",
                            "",
                            ""
                        ),
                        Check(
                            yesNo = YesNo.YES,
                            "",
                            "2 category",
                            "Some check 8\nWith more text",
                            "",
                            ""
                        ),
                        Check(
                            yesNo = YesNo.YES,
                            "",
                            "3 category",
                            "Some check 9",
                            "",
                            ""
                        )
                    ),
                    done = false,
                    checklistId = "",
                    placeId = ""
                ),
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
                checklist = Checklist(
                    ongoing = false,
                    name = "Some audit",
                    repetitiveness = "Every day",
                    lastTimeChecked = "yesterday",
                    checks = listOf(),
                    done = false,
                    checklistId = "",
                    placeId = ""
                ),
                currentCheck = Check(
                    yesNo = YesNo.YES,
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

@Preview
@Composable
private fun ChecklistScreenPreviewDialogLoading() {
    AppTheme {
        ChecklistScreen(
            state = ChecklistState(
                checklist = Checklist(
                    ongoing = false,
                    name = "Some audit",
                    repetitiveness = "Every day",
                    lastTimeChecked = "yesterday",
                    checks = listOf(),
                    done = false,
                    checklistId = "",
                    placeId = ""
                ),
                currentCheck = null
            ), interactor = ChecklistInteractor.Empty()
        )
    }
}