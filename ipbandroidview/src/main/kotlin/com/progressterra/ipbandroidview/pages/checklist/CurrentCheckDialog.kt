package com.progressterra.ipbandroidview.pages.checklist

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
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
import com.progressterra.ipbandroidview.composable.AttachedPhotos
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

private val minDialogHeight = 300.dp

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
            topStart = 12.dp,
            topEnd = 12.dp,
            bottomStart = 0.dp,
            bottomEnd = 0.dp
        ),
        sheetContent = {
            Row(
                modifier = modifier
                    .fillMaxWidth()
                    .heightIn(min = 56.dp)
                    .background(IpbTheme.colors.surface.asBrush())
                    .padding(16.dp),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                Box(contentAlignment = Alignment.Center) {
                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        verticalAlignment = Alignment.CenterVertically,
                        horizontalArrangement = Arrangement.Center,
                    ) {
                        BrushedText(
                            text = state.check.printTitle(),
                            tint = IpbTheme.colors.textPrimary.asBrush(),
                            style = IpbTheme.typography.title,
                            maxLines = 1,
                            textAlign = TextAlign.Center
                        )
                    }

                    Row(
                        Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.End,
                        verticalAlignment = Alignment.CenterVertically,
                    ) {
                        IconButton(
                            onClick = { scope.launch { sheetState.hide() } }
                        ) {
                            BrushedIcon(
                                modifier = modifier,
                                tint = IpbTheme.colors.iconTertiary.asBrush(),
                                resId = R.drawable.ic_cancel
                            )
                        }
                    }


                }
            }
            StateColumn(
                modifier = Modifier
                    .fillMaxWidth()
                    .heightIn(min = minDialogHeight),
                state = state.screen,
                useComponent = useComponent,
            ) {
                val currentCheck = state.check
                val currentCheckMedia = state.media
                Column(
                    modifier = Modifier.padding(
                        top = 8.dp,
                        start = 8.dp,
                        end = 8.dp
                    )
                ) {
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
                    Spacer(modifier = Modifier.size(8.dp))
                    Column(
                        modifier = Modifier
                            .clip(RoundedCornerShape(12.dp))
                            .background(IpbTheme.colors.surface.asBrush())
                    ) {
                        Box(modifier = Modifier.padding(12.dp)) {
                            YesNoButton(
                                modifier = Modifier.fillMaxWidth(),
                                state = currentCheck.yesNo,
                                onClick = {
                                    useComponent.handle(
                                        ChecklistEvent.YesNo(it)
                                    )
                                },
                                enabled = state.status.isOngoing()
                            )
                        }
                        Box(modifier = Modifier.padding(horizontal = 12.dp)) {
                            TextField(
                                modifier = Modifier.fillMaxWidth(),
                                state = state.comment,
                                singleLine = false,
                                useComponent = useComponent
                            )
                        }
                        Box(modifier = Modifier.padding(4.dp)) {
                            Voice(
                                modifier = Modifier.fillMaxWidth(),
                                state = state.voiceState,
                                onStartRecording = {
                                    useComponent.handle(
                                        ChecklistEvent.StartStopRecording
                                    )
                                },
                                onStopRecording = {
                                    useComponent.handle(
                                        ChecklistEvent.StartStopRecording
                                    )
                                },
                                onStartPlay = {
                                    useComponent.handle(
                                        ChecklistEvent.StartPausePlay
                                    )
                                },
                                onPausePlay = {
                                    useComponent.handle(
                                        ChecklistEvent.StartPausePlay
                                    )
                                },
                                onRemove = {
                                    useComponent.handle(ChecklistEvent.RemoveVoice)
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
                                        ChecklistEvent.OnImage(it)
                                    )
                                },
                                onCamera = {
                                    useComponent.handle(
                                        ChecklistEvent.OpenCamera
                                    )
                                })
                        }
                    }
                    if (state.status.isOngoing()) {
                        Spacer(modifier = Modifier.size(8.dp))
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
                    Spacer(modifier = Modifier.size(25.dp))
                }
            }
        },
        sheetBackgroundColor = IpbTheme.colors.surface.asColor(),
        content = content
    )
}