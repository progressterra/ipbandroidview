package com.progressterra.ipbandroidview.ui.checklist

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.ModalBottomSheetLayout
import androidx.compose.material.ModalBottomSheetState
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.unit.dp
import com.progressterra.ipbandroidview.composable.AttachedPhotos
import com.progressterra.ipbandroidview.composable.CheckDialogBar
import com.progressterra.ipbandroidview.shared.ui.StateBox
import com.progressterra.ipbandroidview.composable.VoiceInput
import com.progressterra.ipbandroidview.composable.VoiceState
import com.progressterra.ipbandroidview.composable.YesNoButton
import com.progressterra.ipbandroidview.shared.ui.Button
import com.progressterra.ipbandroidview.shared.ui.ButtonEvent
import com.progressterra.ipbandroidview.shared.ui.ButtonState
import com.progressterra.ipbandroidview.composable.component.TextField
import com.progressterra.ipbandroidview.composable.component.TextFieldState
import com.progressterra.ipbandroidview.shared.ui.UseButton
import com.progressterra.ipbandroidview.composable.component.UseTextField
import com.progressterra.ipbandroidview.core.ScreenState
import com.progressterra.ipbandroidview.model.Check
import com.progressterra.ipbandroidview.model.ChecklistStatus
import com.progressterra.ipbandroidview.model.MultisizedImage
import com.progressterra.ipbandroidview.model.Voice
import com.progressterra.ipbandroidview.shared.theme.IpbTheme
import kotlinx.coroutines.launch

interface UseCurrentCheck : UseTextField, UseButton {

    fun handleEvent(id: String, event: CurrentCheckEvent)
}

sealed class CurrentCheckEvent {

    data class OpenImage(val image: MultisizedImage) : CurrentCheckEvent()

    object Refresh : CurrentCheckEvent()

    data class YesNo(val yesNo: Boolean) : CurrentCheckEvent()

    object StartStopRecording : CurrentCheckEvent()

    object StartPausePlay : CurrentCheckEvent()

    object OpenCamera : CurrentCheckEvent()

    object RemoveVoice : CurrentCheckEvent()
}

data class CurrentCheckState(
    val screenState: ScreenState = ScreenState.LOADING,
    val check: Check = Check(),
    val voiceState: VoiceState = VoiceState.Recorder(false),
    val media: CurrentCheckMedia = CurrentCheckMedia(),
    val status: ChecklistStatus = ChecklistStatus.READ_ONLY,
    val comment: TextFieldState = TextFieldState(),
    val ready: ButtonState = ButtonState()
) {

    fun updateReadyAvailable(available: Boolean) =
        copy(ready = ready.updateEnabled(available))

    fun addVoice(voice: Voice) = copy(media = media.addVoice(voice))

    fun updateCommentAvailable(available: Boolean) =
        copy(comment = comment.updateEnabled(available))

    fun updateComment(newComment: String) = copy(comment = comment.updateText(newComment))

    fun updateStatus(status: ChecklistStatus) = copy(status = status)

    fun updateCheck(check: Check) = copy(check = check)

    fun updateMedia(media: CurrentCheckMedia) = copy(media = media)

    fun updateYesNo(yesNo: Boolean) = copy(check = check.copy(yesNo = yesNo))

    fun updateVoiceState(voiceState: VoiceState) = copy(voiceState = voiceState)

    fun updateScreenState(screenState: ScreenState) = copy(screenState = screenState)

    fun removeImage(image: MultisizedImage) = copy(media = media.removeImage(image))

    fun removeRecord() = copy(voiceState = VoiceState.Recorder(false))

    fun addImage(image: MultisizedImage) = copy(media = media.addImage(image))
}

private val minDialogHeight = 300.dp

/**
 * ready - button
 * commentary - text field
 */
@OptIn(ExperimentalMaterialApi::class)
@Composable
fun CurrentCheckDialog(
    modifier: Modifier = Modifier,
    id: String,
    state: CurrentCheckState,
    sheetState: ModalBottomSheetState,
    useComponent: UseCurrentCheck,
    content: @Composable () -> Unit
) {
    val scope = rememberCoroutineScope()
    ModalBottomSheetLayout(
        modifier = modifier,
        sheetState = sheetState,
        sheetShape = IpbTheme.shapes.dialog,
        sheetContent = {
            CheckDialogBar(currentCheck = state.check,
                onMark = { scope.launch { sheetState.hide() } })
            StateBox(
                modifier = Modifier
                    .fillMaxWidth()
                    .heightIn(min = minDialogHeight),
                state = state.screenState,
                refresh = { useComponent.handleEvent(id, CurrentCheckEvent.Refresh) },
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
                            .clip(IpbTheme.shapes.medium)
                            .background(IpbTheme.colors.surfaces)
                            .padding(12.dp)
                            .fillMaxWidth()
                    ) {
                        Text(
                            text = currentCheck.description,
                            color = IpbTheme.colors.black,
                            style = IpbTheme.typography.text
                        )
                    }
                    Spacer(modifier = Modifier.size(8.dp))
                    Column(
                        modifier = Modifier
                            .clip(IpbTheme.shapes.medium)
                            .background(IpbTheme.colors.surfaces)
                    ) {
                        Box(modifier = Modifier.padding(12.dp)) {
                            YesNoButton(
                                modifier = Modifier.fillMaxWidth(),
                                state = currentCheck.yesNo,
                                onClick = {
                                    useComponent.handleEvent(
                                        id, CurrentCheckEvent.YesNo(it)
                                    )
                                },
                                enabled = state.status.isOngoing()
                            )
                        }
                        Box(modifier = Modifier.padding(horizontal = 12.dp)) {
                            TextField(
                                modifier = Modifier.fillMaxWidth(),
                                id = "commentary",
                                state = state.comment,
                                singleLine = false,
                                useComponent = useComponent
                            )
                        }
                        Box(modifier = Modifier.padding(4.dp)) {
                            VoiceInput(
                                modifier = Modifier.fillMaxWidth(),
                                state = state.voiceState,
                                onStartRecording = {
                                    useComponent.handleEvent(
                                        id, CurrentCheckEvent.StartStopRecording
                                    )
                                },
                                onStopRecording = {
                                    useComponent.handleEvent(
                                        id, CurrentCheckEvent.StartStopRecording
                                    )
                                },
                                onStartPlay = {
                                    useComponent.handleEvent(
                                        id, CurrentCheckEvent.StartPausePlay
                                    )
                                },
                                onPausePlay = {
                                    useComponent.handleEvent(
                                        id, CurrentCheckEvent.StartPausePlay
                                    )
                                },
                                onRemove = {
                                    useComponent.handleEvent(id, CurrentCheckEvent.RemoveVoice)
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
                                    useComponent.handleEvent(
                                        id, CurrentCheckEvent.OpenImage(it)
                                    )
                                },
                                onCamera = {
                                    useComponent.handleEvent(
                                        id, CurrentCheckEvent.OpenCamera
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
                                useComponent = object : UseButton {
                                    override fun handleEvent(id: String, event: ButtonEvent) {
                                        scope.launch { sheetState.hide() }
                                        useComponent.handleEvent(id, event)
                                    }
                                },
                                id = "ready"
                            )
                        }
                    }
                    Spacer(modifier = Modifier.size(25.dp))
                }
            }
        },
        sheetBackgroundColor = IpbTheme.colors.surfaces,
        content = content
    )
}