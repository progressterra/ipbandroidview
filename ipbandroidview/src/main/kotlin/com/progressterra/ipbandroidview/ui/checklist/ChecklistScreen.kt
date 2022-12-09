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
import androidx.compose.foundation.shape.ZeroCornerSize
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
import com.progressterra.ipbandroidview.composable.CheckCard
import com.progressterra.ipbandroidview.composable.CheckDialogBar
import com.progressterra.ipbandroidview.composable.Stats
import com.progressterra.ipbandroidview.composable.ThemedLayout
import com.progressterra.ipbandroidview.composable.ThemedTopAppBar
import com.progressterra.ipbandroidview.composable.VoiceInput
import com.progressterra.ipbandroidview.composable.YesNoButton
import com.progressterra.ipbandroidview.composable.BottomHolder
import com.progressterra.ipbandroidview.composable.Divider
import com.progressterra.ipbandroidview.composable.StateBox
import com.progressterra.ipbandroidview.composable.ThemedButton
import com.progressterra.ipbandroidview.composable.ThemedTextField
import com.progressterra.ipbandroidview.model.checklist.Check
import com.progressterra.ipbandroidview.model.media.MultisizedImage
import com.progressterra.ipbandroidview.model.checklist.ChecklistStatus
import com.progressterra.ipbandroidview.theme.AppTheme
import kotlinx.coroutines.launch

@OptIn(ExperimentalMaterialApi::class)
@Composable
fun ChecklistScreen(
    state: () -> ChecklistState,
    back: () -> Unit,
    refreshCheck: () -> Unit,
    refreshChecklist: () -> Unit,
    openCheck: (Check) -> Unit,
    applyCheck: () -> Unit,
    startStopAudit: () -> Unit,
    yesNo: (Boolean) -> Unit,
    editCheckCommentary: (String) -> Unit,
    startPausePlay: () -> Unit,
    startStopRecording: () -> Unit,
    remove: () -> Unit,
    openImage: (MultisizedImage) -> Unit,
    onCamera: () -> Unit,
    editEmail: (String) -> Unit,
    sendEmail: () -> Unit
) {
    val sheetState = rememberModalBottomSheetState(
        initialValue = ModalBottomSheetValue.Hidden, skipHalfExpanded = true
    )
    val coroutineScope = rememberCoroutineScope()
    val ongoing = { state().status == ChecklistStatus.ONGOING }
    ModalBottomSheetLayout(
        sheetState = sheetState, sheetShape = AppTheme.shapes.medium.copy(
            bottomEnd = ZeroCornerSize, bottomStart = ZeroCornerSize
        ), sheetContent = {
            CheckDialogBar(currentCheck = state()::currentCheck,
                onMark = { coroutineScope.launch { sheetState.hide() } })
            StateBox(
                modifier = Modifier
                    .fillMaxWidth()
                    .heightIn(min = 300.dp),
                state = state()::checkScreenState,
                refresh = refreshCheck
            ) {
                val currentCheck = state().currentCheck
                val currentCheckMedia = state().currentCheckMedia
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
                                    state = currentCheck::yesNo,
                                    onClick = yesNo,
                                    enabled = ongoing
                                )
                            }
                            Box(modifier = Modifier.padding(horizontal = AppTheme.dimensions.medium)) {
                                ThemedTextField(
                                    modifier = Modifier.fillMaxWidth(),
                                    text = currentCheck::comment,
                                    hint = stringResource(id = R.string.text_comment),
                                    onChange = editCheckCommentary,
                                    enabled = ongoing,
                                    singleLine = false
                                )
                            }
                            Box(modifier = Modifier.padding(AppTheme.dimensions.tiny)) {
                                VoiceInput(
                                    modifier = Modifier.fillMaxWidth(),
                                    state = state()::voiceState,
                                    onStartRecording = startStopRecording,
                                    onStopRecording = startStopRecording,
                                    onStartPlay = startPausePlay,
                                    onPausePlay = startPausePlay,
                                    onRemove = remove,
                                    enabled = ongoing
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
                                    enabled = ongoing,
                                    pictures = { currentCheckMedia.pictures.filter { !it.toRemove } },
                                    onPhotoSelect = openImage,
                                    onCamera = onCamera
                                )
                            }
                        }
                        if (ongoing()) {
                            Spacer(modifier = Modifier.size(AppTheme.dimensions.small))
                            Row(Modifier.padding(horizontal = AppTheme.dimensions.small)) {
                                ThemedButton(
                                    modifier = Modifier.fillMaxWidth(), onClick = {
                                        applyCheck()
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
                onBack = back, title = stringResource(id = R.string.audit)
            )
        }, bottomBar = {
            BottomHolder {
                Row(horizontalArrangement = Arrangement.spacedBy(AppTheme.dimensions.small)) {
                    if (state().status == ChecklistStatus.ONGOING) {
                        if (state().stats.remaining >= 1)
                            ThemedButton(
                                modifier = Modifier.weight(1f),
                                onClick = startStopAudit,
                                text = stringResource(id = R.string.end_audit),
                                tint = AppTheme.colors.secondary,
                                textColor = AppTheme.colors.gray1,
                                enabled = state().checklistScreenState::isSuccess
                            )
                        else
                            ThemedButton(
                                modifier = Modifier.weight(1f),
                                onClick = startStopAudit,
                                text = stringResource(id = R.string.end_audit),
                                enabled = state().checklistScreenState::isSuccess
                            )
                        Stats(modifier = Modifier.weight(1f), stats = state()::stats)
                    }
                    if (state().status == ChecklistStatus.CAN_BE_STARTED)
                        ThemedButton(
                            modifier = Modifier.fillMaxWidth(),
                            onClick = startStopAudit,
                            text = stringResource(R.string.start_audit),
                            enabled = state().checklistScreenState::isSuccess
                        )
                }
                if (state().status == ChecklistStatus.READ_ONLY) {
                    Spacer(modifier = Modifier.size(AppTheme.dimensions.small))
                    ThemedTextField(
                        modifier = Modifier.fillMaxWidth(),
                        text = state()::email,
                        onChange = editEmail,
                        hint = stringResource(R.string.email),
                        action = sendEmail,
                        enabled = state().checklistScreenState::isSuccess
                    )
                    Spacer(modifier = Modifier.size(AppTheme.dimensions.small))
                    ThemedButton(
                        modifier = Modifier.fillMaxWidth(),
                        onClick = sendEmail,
                        text = stringResource(R.string.send_on_email),
                        enabled = state().checklistScreenState::isSuccess
                    )
                }
            }

        }, bottomOverlap = true) { _, bottomPadding ->
            StateBox(
                modifier = Modifier.fillMaxSize(),
                state = state()::checklistScreenState,
                refresh = refreshChecklist
            ) {
                val groupedChecks by remember(state().checks) {
                    mutableStateOf(state().checks.groupBy { it.categoryNumber })
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
                            name = state().auditDocument::name,
                            checkCounter = state().checks::size
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
                                openCheck(it)
                                coroutineScope.launch { sheetState.show() }
                            }, state = { it })
                        }
                    }
                    item { Spacer(modifier = Modifier.size(bottomPadding)) }
                }
            }
        }
    }
}
