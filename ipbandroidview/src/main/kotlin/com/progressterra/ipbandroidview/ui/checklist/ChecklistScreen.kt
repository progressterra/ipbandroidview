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
import androidx.compose.material.IconButton
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
import com.progressterra.ipbandroidview.components.AttachedPhoto
import com.progressterra.ipbandroidview.components.AuditTitle
import com.progressterra.ipbandroidview.components.BottomHolder
import com.progressterra.ipbandroidview.components.CategoryDivider
import com.progressterra.ipbandroidview.components.CheckCard
import com.progressterra.ipbandroidview.components.MarkIcon
import com.progressterra.ipbandroidview.components.StateBox
import com.progressterra.ipbandroidview.components.ThemedButton
import com.progressterra.ipbandroidview.components.ThemedLayout
import com.progressterra.ipbandroidview.components.ThemedTextField
import com.progressterra.ipbandroidview.components.VoiceInput
import com.progressterra.ipbandroidview.components.bar.ThemedTopAppBar
import com.progressterra.ipbandroidview.components.bar.ThemedTopDialogBar
import com.progressterra.ipbandroidview.components.button.YesNoButton
import com.progressterra.ipbandroidview.components.stats.Stats
import com.progressterra.ipbandroidview.model.CheckPicture
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
    openImage: (CheckPicture) -> Unit,
    onCamera: () -> Unit
) {
    val sheetState = rememberModalBottomSheetState(
        initialValue = ModalBottomSheetValue.Hidden, skipHalfExpanded = true
    )
    val coroutineScope = rememberCoroutineScope()
    ModalBottomSheetLayout(
        sheetState = sheetState,
        sheetShape = AppTheme.shapes.medium.copy(
            bottomEnd = ZeroCornerSize,
            bottomStart = ZeroCornerSize
        ),
        sheetContent = {
            ThemedTopDialogBar(title = {
                if (state().currentCheck == null) stringResource(id = R.string.loading) else "${
                    stringResource(id = R.string.question)
                } ${state().currentCheck?.categoryNumber ?: ""}-${state().currentCheck?.ordinal ?: ""}"
            },
                rightActions = {
                    IconButton(
                        onClick = { coroutineScope.launch { sheetState.hide() } }) {
                        MarkIcon()
                    }
                })
            StateBox(
                modifier = Modifier
                    .fillMaxWidth()
                    .heightIn(min = 300.dp),
                state = state()::checkScreenState,
                onRefresh = refreshCheck
            ) {
                if (state().currentCheck != null && state().currentCheckMedia != null) {
                    Column(
                        modifier = Modifier
                            .padding(
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
                                text = state().currentCheck?.description ?: "",
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
                                    state = { state().currentCheck?.yesNo },
                                    onClick = yesNo,
                                    enabled = state().auditDocument::ongoing
                                )
                            }
                            Box(modifier = Modifier.padding(horizontal = AppTheme.dimensions.medium)) {
                                ThemedTextField(
                                    modifier = Modifier.fillMaxWidth(),
                                    text = { state().currentCheck?.comment ?: "" },
                                    hint =
                                    stringResource(
                                        id = R.string.text_comment
                                    ),
                                    onChange = editCheckCommentary,
                                    enabled = state().auditDocument::ongoing,
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
                                    enabled = state().auditDocument::ongoing
                                )
                            }
                            Box(
                                modifier = Modifier.padding(
                                    start = AppTheme.dimensions.medium,
                                    end = AppTheme.dimensions.medium,
                                    bottom = AppTheme.dimensions.medium
                                )
                            ) {
                                AttachedPhoto(
                                    modifier = Modifier.fillMaxWidth(),
                                    enabled = state().auditDocument::ongoing,
                                    pictures = {
                                        state().currentCheckMedia?.pictures?.filter { !it.toRemove }
                                            ?: emptyList()
                                    },
                                    onPhotoSelect = openImage,
                                    onCamera = onCamera
                                )
                            }
                        }
                        if (state().auditDocument.ongoing) {
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
        },
        sheetBackgroundColor = AppTheme.colors.surfaces
    ) {
        ThemedLayout(topBar = {
            ThemedTopAppBar(
                onBack = back, title = { stringResource(id = R.string.audit) }
            )
        }, bottomBar = {
            if (!(state().auditDocument.readOrCompleteOnly && !state().auditDocument.ongoing)) {
                BottomHolder(Modifier.fillMaxWidth()) {
                    Row(horizontalArrangement = Arrangement.spacedBy(AppTheme.dimensions.small)) {
                        if (state().auditDocument.ongoing)
                            if (state().stats.remaining >= 1)
                                ThemedButton(
                                    modifier = Modifier.weight(1f),
                                    onClick = startStopAudit,
                                    text =
                                    stringResource(
                                        id = R.string.end_audit
                                    ),
                                    tint = AppTheme.colors.secondary,
                                    textColor = AppTheme.colors.gray1,
                                    enabled = state().checklistScreenState::isSuccess
                                )
                            else
                                ThemedButton(
                                    modifier = Modifier.weight(1f),
                                    onClick = startStopAudit,
                                    text =
                                    stringResource(
                                        id = R.string.end_audit
                                    ),
                                    tint = AppTheme.colors.primary,
                                    textColor = AppTheme.colors.surfaces,
                                    enabled = state().checklistScreenState::isSuccess
                                )
                        else
                            ThemedButton(
                                modifier = Modifier.weight(1f),
                                onClick = startStopAudit,
                                text =
                                stringResource(
                                    R.string.start_audit
                                ),
                                tint = AppTheme.colors.primary,
                                textColor = AppTheme.colors.surfaces,
                                enabled = state().checklistScreenState::isSuccess
                            )
                        if (state().auditDocument.ongoing) {
                            Stats(modifier = Modifier.weight(1f), stats = state()::stats)
                        }
                    }
                }
            }
        }, bottomOverlap = true) { _, bottomPadding ->
            StateBox(
                modifier = Modifier.fillMaxSize(),
                state = state()::checklistScreenState,
                onRefresh = refreshChecklist
            ) {
                val groupedChecks by remember(state().checks) {
                    mutableStateOf(state().checks.groupBy { it.categoryNumber })
                }
                LazyColumn(
                    modifier = Modifier
                        .fillMaxSize(),
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
                    groupedChecks.forEach { (category, checks) ->
                        item {
                            CategoryDivider(
                                modifier = Modifier.fillMaxWidth(), title = {
                                    "$category. ${
                                        checks.firstOrNull()?.category ?: stringResource(id = R.string.no_data)
                                    }"
                                }
                            )
                        }
                        items(checks) {
                            CheckCard(
                                modifier = Modifier.fillMaxWidth(), onClick = {
                                    openCheck(it)
                                    coroutineScope.launch { sheetState.show() }
                                }, name = { it.name }, yesNo = { it.yesNo }
                            )
                        }
                    }
                    item { Spacer(modifier = Modifier.size(bottomPadding)) }
                }
            }
        }
    }
}
