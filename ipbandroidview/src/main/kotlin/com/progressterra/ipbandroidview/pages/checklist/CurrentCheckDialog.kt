package com.progressterra.ipbandroidview.pages.checklist

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.IconButton
import androidx.compose.material.ModalBottomSheetLayout
import androidx.compose.material.ModalBottomSheetState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.progressterra.ipbandroidview.R
import com.progressterra.ipbandroidview.features.attachedphotos.AttachedPhotos
import com.progressterra.ipbandroidview.features.voice.Voice
import com.progressterra.ipbandroidview.features.yesno.YesNoButton
import com.progressterra.ipbandroidview.shared.theme.IpbTheme
import com.progressterra.ipbandroidview.shared.ui.BrushedIcon
import com.progressterra.ipbandroidview.shared.ui.BrushedText
import com.progressterra.ipbandroidview.shared.ui.button.Button
import com.progressterra.ipbandroidview.shared.ui.button.ButtonEvent
import com.progressterra.ipbandroidview.shared.ui.button.UseButton
import com.progressterra.ipbandroidview.shared.ui.statecolumn.StateColumn
import com.progressterra.ipbandroidview.shared.ui.textfield.TextField
import kotlinx.coroutines.launch

/**
 * ready - button
 * commentary - text field
 */
@OptIn(ExperimentalMaterialApi::class)
@Composable
fun CurrentCheckDialog(
    modifier: Modifier = Modifier,
    state: CurrentCheckState,
    sheetState: ModalBottomSheetState,
    useComponent: UseChecklistScreen,
    content: @Composable () -> Unit
) {
    val scope = rememberCoroutineScope()
    ModalBottomSheetLayout(
        modifier = modifier,
        sheetState = sheetState,
        sheetShape = RoundedCornerShape(
            topStart = 16.dp,
            topEnd = 16.dp,
            bottomStart = 0.dp,
            bottomEnd = 0.dp
        ),
        sheetContent = {
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .heightIn(min = 56.dp)
                    .background(IpbTheme.colors.surface.asBrush())
                    .padding(horizontal = 16.dp, vertical = 12.dp)
            ) {
                BrushedText(
                    modifier = Modifier.align(Alignment.Center),
                    text = state.check.printTitle(),
                    tint = IpbTheme.colors.textPrimary.asBrush(),
                    style = IpbTheme.typography.title,
                    maxLines = 1,
                    textAlign = TextAlign.Center
                )
                IconButton(
                    modifier = Modifier.align(Alignment.CenterEnd),
                    onClick = { scope.launch { sheetState.hide() } }
                ) {
                    BrushedIcon(
                        modifier = modifier,
                        tint = IpbTheme.colors.iconTertiary.asBrush(),
                        resId = R.drawable.ic_cancel
                    )
                }
            }
            StateColumn(
                modifier = Modifier
                    .fillMaxWidth()
                    .heightIn(min = 300.dp)
                    .background(IpbTheme.colors.background.asBrush())
                    .padding(
                        horizontal = 16.dp,
                        vertical = 8.dp
                    ),
                maxSize = false,
                state = state.screen,
                useComponent = useComponent,
                verticalArrangement = Arrangement.spacedBy(8.dp)
            ) {
                val currentCheck = state.check
                val currentCheckMedia = state.media
                Column(
                    modifier = Modifier
                        .clip(RoundedCornerShape(12.dp))
                        .background(IpbTheme.colors.surface.asBrush())
                        .padding(12.dp)
                        .fillMaxWidth()
                ) {
                    BrushedText(
                        text = currentCheck.description,
                        tint = IpbTheme.colors.textPrimary.asBrush(),
                        style = IpbTheme.typography.body
                    )
                }
                Column(
                    modifier = Modifier
                        .clip(RoundedCornerShape(12.dp))
                        .background(IpbTheme.colors.surface.asBrush())
                ) {
                    YesNoButton(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(8.dp),
                        state = currentCheck.yesNo,
                        onClick = {
                            useComponent.handle(
                                ChecklistScreenEvent.YesNo(it)
                            )
                        },
                        enabled = state.status.isOngoing()
                    )
                    TextField(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(8.dp),
                        backgroundColor = IpbTheme.colors.onSurface.asColor(),
                        state = state.comment,
                        singleLine = false,
                        useComponent = useComponent
                    )
                    Box(modifier = Modifier.padding(4.dp)) {
                        Voice(
                            modifier = Modifier.fillMaxWidth(),
                            state = state.voiceState,
                            onStartRecording = {
                                useComponent.handle(
                                    ChecklistScreenEvent.StartStopRecording
                                )
                            },
                            onStopRecording = {
                                useComponent.handle(
                                    ChecklistScreenEvent.StartStopRecording
                                )
                            },
                            onStartPlay = {
                                useComponent.handle(
                                    ChecklistScreenEvent.StartPausePlay
                                )
                            },
                            onPausePlay = {
                                useComponent.handle(
                                    ChecklistScreenEvent.StartPausePlay
                                )
                            },
                            onRemove = {
                                useComponent.handle(ChecklistScreenEvent.RemoveVoice)
                            },
                            enabled = state.status.isOngoing()
                        )
                    }
                    Box(
                        modifier = Modifier.padding(
                            start = 12.dp,
                            end = 12.dp,
                            bottom = 12.dp
                        )
                    ) {
                        AttachedPhotos(modifier = Modifier.fillMaxWidth(),
                            enabled = state.status.isOngoing(),
                            pictures = currentCheckMedia.pictures.filter { !it.toRemove },
                            onPhotoSelect = {
                                useComponent.handle(
                                    ChecklistScreenEvent.OnImage(it)
                                )
                            },
                            onCamera = {
                                useComponent.handle(
                                    ChecklistScreenEvent.OpenCamera
                                )
                            })
                    }
                }
                if (state.status.isOngoing()) {
                    Row(Modifier.padding(horizontal = 8.dp)) {
                        Button(
                            modifier = Modifier.fillMaxWidth(),
                            state = state.ready,
                            title = stringResource(R.string.ready),
                            useComponent = object : UseButton {

                                override fun handle(event: ButtonEvent) {
                                    scope.launch { sheetState.hide() }
                                    useComponent.handle(event)
                                }
                            }
                        )
                    }
                }
            }
        },
        sheetBackgroundColor = IpbTheme.colors.surface.asColor(),
        content = content
    )
}