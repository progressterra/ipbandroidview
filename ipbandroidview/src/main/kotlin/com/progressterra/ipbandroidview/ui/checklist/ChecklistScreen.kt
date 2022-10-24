package com.progressterra.ipbandroidview.ui.checklist

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.ModalBottomSheetLayout
import androidx.compose.material.ModalBottomSheetValue
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.material.rememberModalBottomSheetState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.onGloballyPositioned
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.progressterra.ipbandroidview.R
import com.progressterra.ipbandroidview.composable.AttachedPhoto
import com.progressterra.ipbandroidview.composable.AuditTitle
import com.progressterra.ipbandroidview.composable.BottomHolder
import com.progressterra.ipbandroidview.composable.CategoryDivider
import com.progressterra.ipbandroidview.composable.CheckCard
import com.progressterra.ipbandroidview.composable.StateBox
import com.progressterra.ipbandroidview.composable.ThemedButton
import com.progressterra.ipbandroidview.composable.ThemedNotebook
import com.progressterra.ipbandroidview.composable.ThemedTopAppBar
import com.progressterra.ipbandroidview.composable.ThemedTopDialogBar
import com.progressterra.ipbandroidview.composable.VoiceInput
import com.progressterra.ipbandroidview.composable.stats.Stats
import com.progressterra.ipbandroidview.composable.yesno.YesNoButton
import com.progressterra.ipbandroidview.theme.AppTheme
import kotlinx.coroutines.launch

@Suppress("unused")
@OptIn(ExperimentalMaterialApi::class)
@Composable
fun ChecklistScreen(state: ChecklistState, interactor: ChecklistInteractor) {
    val sheetState = rememberModalBottomSheetState(
        initialValue = ModalBottomSheetValue.Hidden, skipHalfExpanded = true
    )
    val coroutineScope = rememberCoroutineScope()
    ModalBottomSheetLayout(
        sheetState = sheetState, sheetShape = RoundedCornerShape(
            topStart = 8.dp, topEnd = 8.dp
        ), sheetContent = {
            ThemedTopDialogBar(title = state.currentCheckTitle, rightActions = {
                IconButton(modifier = Modifier.size(24.dp),
                    onClick = { coroutineScope.launch { sheetState.hide() } }) {
                    Icon(
                        painter = painterResource(id = R.drawable.ic_mark),
                        contentDescription = stringResource(R.string.close),
                        tint = AppTheme.colors.gray1
                    )
                }
            })
            StateBox(modifier = Modifier
                .fillMaxWidth()
                .heightIn(min = 300.dp),
                state = state.screenState,
                onRefresh = { interactor.refresh() }) {
                if (state.currentCheck != null && state.currentCheckMedia != null) {
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
                                    state = state.voiceState,
                                    onStartRecording = { interactor.startStopRecording() },
                                    onStopRecording = { interactor.startStopRecording() },
                                    onStartPlay = { interactor.startPausePlay() },
                                    onPausePlay = { interactor.startPausePlay() },
                                    onRemove = {
                                        interactor.removeRecord()
                                    },
                                    enabled = state.checklist.ongoing
                                )
                            }
                            Box(
                                modifier = Modifier.padding(
                                    top = 4.dp, start = 12.dp, end = 12.dp, bottom = 12.dp
                                )
                            ) {
                                AttachedPhoto(modifier = Modifier.fillMaxWidth(),
                                    enabled = state.checklist.ongoing,
                                    pictures = state.currentCheckMedia.pictures.filter { !it.toRemove },
                                    onPhotoSelect = { interactor.openImage(it) },
                                    onCamera = { interactor.onCamera() })
                            }
                        }
                        if (state.checklist.ongoing) {
                            Spacer(modifier = Modifier.size(25.dp))
                            Row(Modifier.padding(horizontal = 8.dp)) {
                                ThemedButton(
                                    modifier = Modifier.fillMaxWidth(), onClick = {
                                        interactor.applyCheck()
                                        coroutineScope.launch { sheetState.hide() }
                                    }, text = stringResource(id = R.string.ready)
                                )
                            }
                        }
                        Spacer(modifier = Modifier.size(25.dp))
                    }
                }
            }
        }, sheetBackgroundColor = AppTheme.colors.surfaces
    ) {
        Scaffold(topBar = {
            ThemedTopAppBar(
                onBack = { interactor.back() }, title = stringResource(id = R.string.audit)
            )
        }) {
            Box(
                modifier = Modifier
                    .fillMaxSize()
                    .background(AppTheme.colors.background)
            ) {
                var spacerSize by remember { mutableStateOf(0.dp) }
                LazyColumn(
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(
                            top = 8.dp, start = 8.dp, end = 8.dp
                        ), verticalArrangement = Arrangement.spacedBy(8.dp)
                ) {
                    item {
                        AuditTitle(
                            modifier = Modifier.fillMaxWidth(),
                            name = state.checklist.name,
                            checkCounter = state.checklist.checks.size
                        )
                    }
                    state.checklist.checks.groupBy { it.category }.toSortedMap()
                        .forEach { (category, checks) ->
                            item {
                                CategoryDivider(
                                    modifier = Modifier.fillMaxWidth(), title = category
                                )
                            }
                            items(checks.sortedBy { it.name }) {
                                CheckCard(
                                    modifier = Modifier.fillMaxWidth(), onClick = {
                                        interactor.check(it)
                                        coroutineScope.launch { sheetState.show() }
                                    }, name = it.name, yesNo = it.yesNo
                                )
                            }
                        }
                    item { Spacer(modifier = Modifier.size(spacerSize + 8.dp)) }
                }
                if (!state.checklist.done) {
                    val density = LocalDensity.current
                    BottomHolder(
                        Modifier
                            .fillMaxWidth()
                            .align(Alignment.BottomCenter)
                            .onGloballyPositioned {
                                spacerSize = with(density) { it.size.height.toDp() }
                            }) {
                        Row {
                            ThemedButton(
                                modifier = Modifier.weight(1f),
                                onClick = { interactor.startStopAudit() },
                                text = stringResource(
                                    id = if (state.checklist.ongoing) R.string.end_audit else R.string.start_audit
                                ),
                                tint = if (state.stats.remaining >= 1 && state.checklist.ongoing) Color(
                                    0xFFA0ECAC
                                ) else AppTheme.colors.primary,
                                textColor = if (state.stats.remaining >= 1 && state.checklist.ongoing) AppTheme.colors.gray1 else AppTheme.colors.surfaces
                            )
                            if (state.checklist.ongoing) {
                                Spacer(modifier = Modifier.size(8.dp))
                                Stats(modifier = Modifier.weight(1f), stats = state.stats)
                            }
                        }
                    }
                }
            }
        }
    }
}
//
//@Preview
//@Composable
//private fun ChecklistScreenPreview() {
//    AppTheme {
//        ChecklistScreen(
//            state = ChecklistState(
//                checklist = Checklist(
//                    ongoing = true,
//                    name = "Some audit",
//                    repetitiveness = "Every day",
//                    lastTimeChecked = "yesterday",
//                    checks = listOf(
//                        Check(
//                            yesNo = YesNo.YES,
//                            "",
//                            "1 category",
//                            "Some check 1\nWith more text",
//                            "",
//                            ""
//                        ), Check(
//                            yesNo = YesNo.YES, "", "1 category", "Some check 2", "", ""
//                        ), Check(
//                            yesNo = YesNo.YES, "", "1 category", "Some check 3", "", ""
//                        ), Check(
//                            yesNo = YesNo.YES, "", "1 category", "Some check 4", "", ""
//                        ), Check(
//                            yesNo = YesNo.YES,
//                            "",
//                            "1 category",
//                            "Some check 5\nWith more text",
//                            "",
//                            ""
//                        ), Check(
//                            yesNo = YesNo.YES, "", "2 category", "Some check 6", "", ""
//                        ), Check(
//                            yesNo = YesNo.YES, "", "2 category", "Some check 7", "", ""
//                        ), Check(
//                            yesNo = YesNo.YES,
//                            "",
//                            "2 category",
//                            "Some check 8\nWith more text",
//                            "",
//                            ""
//                        ), Check(
//                            yesNo = YesNo.YES, "", "3 category", "Some check 9", "", ""
//                        )
//                    ),
//                    done = false,
//                    checklistId = "",
//                    placeId = "",
//                    documentId = null
//                ), stats = ChecklistStats(14, 12, 1, 1)
//            ), interactor = ChecklistInteractor.Empty()
//        )
//    }
//}