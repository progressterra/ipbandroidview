package com.progressterra.ipbandroidview.ui.checklist

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.ModalBottomSheetLayout
import androidx.compose.material.ModalBottomSheetValue
import androidx.compose.material.Text
import androidx.compose.material.rememberModalBottomSheetState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.progressterra.ipbandroidview.R
import com.progressterra.ipbandroidview.composable.AttachedPhotos
import com.progressterra.ipbandroidview.composable.AuditTitle
import com.progressterra.ipbandroidview.composable.BottomHolder
import com.progressterra.ipbandroidview.composable.CheckCard
import com.progressterra.ipbandroidview.composable.CheckDialogBar
import com.progressterra.ipbandroidview.composable.Divider
import com.progressterra.ipbandroidview.composable.StateBox
import com.progressterra.ipbandroidview.composable.Stats
import com.progressterra.ipbandroidview.composable.component.ButtonComponent
import com.progressterra.ipbandroidview.composable.ThemedLayout
import com.progressterra.ipbandroidview.composable.component.ThemedTextField
import com.progressterra.ipbandroidview.composable.ThemedTopAppBar
import com.progressterra.ipbandroidview.composable.VoiceInput
import com.progressterra.ipbandroidview.composable.YesNoButton
import com.progressterra.ipbandroidview.theme.AppTheme
import kotlinx.coroutines.launch

private val minDialogHeight = 300.dp

@OptIn(ExperimentalMaterialApi::class)
@Composable
fun ChecklistScreen(
    state: ChecklistState,
    interactor: ChecklistInteractor
) {
    val sheetState = rememberModalBottomSheetState(
        initialValue = ModalBottomSheetValue.Hidden, skipHalfExpanded = true
    )
    val coroutineScope = rememberCoroutineScope()
    ModalBottomSheetLayout(
        sheetState = sheetState, sheetShape = AppTheme.shapes.dialog, sheetContent = {
            CheckDialogBar(currentCheck = state.currentCheck,
                onMark = { coroutineScope.launch { sheetState.hide() } })
            StateBox(
                modifier = Modifier
                    .fillMaxWidth()
                    .heightIn(min = minDialogHeight),
                state = state.checkScreenState,
                refresh = { interactor.refreshCheck() }
            ) {
                val currentCheck = state.currentCheck
                val currentCheckMedia = state.currentCheckMedia
                if (currentCheck != null && currentCheckMedia != null) {
                    Column(
                        modifier = Modifier.padding(
                            top = AppTheme.dimensions.small,
                            start = AppTheme.dimensions.small,
                            end = AppTheme.dimensions.small
                        )
                    ) {
                        Column(
                            modifier = Modifier
                                .clip(AppTheme.shapes.medium)
                                .background(AppTheme.colors.surfaces)
                                .padding(AppTheme.dimensions.medium)
                                .fillMaxWidth()
                        ) {
                            Text(
                                text = currentCheck.description,
                                color = AppTheme.colors.black,
                                style = AppTheme.typography.text
                            )
                        }
                        Spacer(modifier = Modifier.size(AppTheme.dimensions.small))
                        Column(
                            modifier = Modifier
                                .clip(AppTheme.shapes.medium)
                                .background(AppTheme.colors.surfaces)
                        ) {
                            Box(modifier = Modifier.padding(AppTheme.dimensions.medium)) {
                                YesNoButton(
                                    modifier = Modifier.fillMaxWidth(),
                                    state = currentCheck.yesNo,
                                    onClick = { interactor.yesNo(it) },
                                    enabled = state.status.isOngoing()
                                )
                            }
                            Box(modifier = Modifier.padding(horizontal = AppTheme.dimensions.medium)) {
                                ThemedTextField(
                                    modifier = Modifier.fillMaxWidth(),
                                    text = currentCheck.comment,
                                    hint = stringResource(id = R.string.text_comment),
                                    onChange = { interactor.editCheckCommentary(it) },
                                    enabled = state.status.isOngoing(),
                                    singleLine = false
                                )
                            }
                            Box(modifier = Modifier.padding(AppTheme.dimensions.tiny)) {
                                VoiceInput(
                                    modifier = Modifier.fillMaxWidth(),
                                    state = state.voiceState,
                                    onStartRecording = { interactor.startStopRecording() },
                                    onStopRecording = { interactor.startStopRecording() },
                                    onStartPlay = { interactor.startPausePlay() },
                                    onPausePlay = { interactor.startPausePlay() },
                                    onRemove = { interactor.remove() },
                                    enabled = state.status.isOngoing()
                                )
                            }
                            Box(
                                modifier = Modifier.padding(
                                    start = AppTheme.dimensions.medium,
                                    end = AppTheme.dimensions.medium,
                                    bottom = AppTheme.dimensions.medium
                                )
                            ) {
                                AttachedPhotos(
                                    modifier = Modifier.fillMaxWidth(),
                                    enabled = state.status.isOngoing(),
                                    pictures = currentCheckMedia.pictures.filter { !it.toRemove },
                                    onPhotoSelect = { interactor.openImage(it) },
                                    onCamera = { interactor.onCamera() }
                                )
                            }
                        }
                        if (state.status.isOngoing()) {
                            Spacer(modifier = Modifier.size(AppTheme.dimensions.small))
                            Row(Modifier.padding(horizontal = AppTheme.dimensions.small)) {
                                ButtonComponent(
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
        ThemedLayout(topBar = {
            ThemedTopAppBar(
                onBack = { interactor.onBack() }, title = stringResource(id = R.string.audit)
            )
        }, bottomBar = {
            BottomHolder {
                Row(horizontalArrangement = Arrangement.spacedBy(AppTheme.dimensions.small)) {
                    if (state.status.isOngoing()) {
                        if (state.stats.remaining >= 1)
                            ButtonComponent(
                                modifier = Modifier.weight(1f),
                                onClick = { interactor.startStopAudit() },
                                text = stringResource(id = R.string.end_audit),
                                tint = AppTheme.colors.secondary,
                                textColor = AppTheme.colors.gray1,
                                enabled = state.checklistScreenState.isSuccess()
                            )
                        else
                            ButtonComponent(
                                modifier = Modifier.weight(1f),
                                onClick = { interactor.startStopAudit() },
                                text = stringResource(id = R.string.end_audit),
                                enabled = state.checklistScreenState.isSuccess()
                            )
                        Stats(modifier = Modifier.weight(1f), stats = state.stats)
                    }
                    if (state.status.isCanBeStarted())
                        ButtonComponent(
                            modifier = Modifier.fillMaxWidth(),
                            onClick = { interactor.startStopAudit() },
                            text = stringResource(R.string.start_audit),
                            enabled = state.checklistScreenState.isSuccess()
                        )
                }
            }
        }, bottomOverlap = true) { _, bottomPadding ->
            StateBox(
                modifier = Modifier.fillMaxSize(),
                state = state.checklistScreenState,
                refresh = { interactor.refreshChecklist() }
            ) {
                val groupedChecks by remember(state.checks) {
                    mutableStateOf(state.checks.groupBy { it.categoryNumber })
                }
                LazyColumn(
                    modifier = Modifier.fillMaxSize(),
                    verticalArrangement = Arrangement.spacedBy(AppTheme.dimensions.small),
                    contentPadding = PaddingValues(
                        start = AppTheme.dimensions.small,
                        top = AppTheme.dimensions.small,
                        end = AppTheme.dimensions.small
                    )
                ) {
                    item {
                        AuditTitle(
                            modifier = Modifier.fillMaxWidth(),
                            name = state.auditDocument.name,
                            checkCounter = state.checks.size
                        )
                    }
                    groupedChecks.forEach { (_, checks) ->
                        item {
                            Divider(
                                modifier = Modifier.fillMaxWidth(),
                                title = checks.firstOrNull()?.printCategory()
                                    ?: stringResource(R.string.no_data)
                            )
                        }
                        items(checks) {
                            CheckCard(modifier = Modifier.fillMaxWidth(), onClick = {
                                interactor.openCheck(it)
                                coroutineScope.launch { sheetState.show() }
                            }, state = it)
                        }
                    }
                    item { Spacer(modifier = Modifier.size(bottomPadding)) }
                }
            }
        }
    }
}
