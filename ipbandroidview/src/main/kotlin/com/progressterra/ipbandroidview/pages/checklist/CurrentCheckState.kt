package com.progressterra.ipbandroidview.pages.checklist

import androidx.compose.runtime.Immutable
import com.progressterra.ipbandroidview.entities.Check
import com.progressterra.ipbandroidview.entities.ChecklistStatus
import com.progressterra.ipbandroidview.entities.MultisizedImage
import com.progressterra.ipbandroidview.entities.Voice
import com.progressterra.ipbandroidview.features.voice.VoiceState
import com.progressterra.ipbandroidview.shared.ui.button.ButtonState
import com.progressterra.ipbandroidview.shared.ui.statecolumn.ScreenState
import com.progressterra.ipbandroidview.shared.ui.statecolumn.StateColumnState
import com.progressterra.ipbandroidview.shared.ui.textfield.TextFieldState

@Immutable
data class CurrentCheckState(
    val screen: StateColumnState = StateColumnState(id = "dialog"),
    val check: Check = Check(),
    val voiceState: VoiceState = VoiceState.Recorder(false),
    val media: CurrentCheckMedia = CurrentCheckMedia(),
    val status: ChecklistStatus = ChecklistStatus.READ_ONLY,
    val comment: TextFieldState = TextFieldState(id = "comment"),
    val ready: ButtonState = ButtonState(id = "ready")
) {

    fun updateReadyAvailable(available: Boolean) =
        copy(ready = ready.copy(enabled = available))

    fun addVoice(voice: Voice) = copy(media = media.addVoice(voice))

    fun updateCommentAvailable(available: Boolean) =
        copy(comment = comment.copy(enabled = available))

    fun updateComment(newComment: String) =
        copy(comment = comment.copy(text = newComment), check = check.copy(comment = newComment))

    fun updateStatus(status: ChecklistStatus) = copy(status = status)

    fun updateCheck(check: Check) = copy(check = check)

    fun updateMedia(media: CurrentCheckMedia) = copy(media = media)

    fun updateYesNo(yesNo: Boolean) = copy(check = check.copy(yesNo = yesNo))

    fun updateVoiceState(voiceState: VoiceState) = copy(voiceState = voiceState)

    fun updateScreenState(screenState: ScreenState) = copy(screen = screen.copy(state = screenState))

    fun removeImage(image: MultisizedImage) = copy(media = media.removeImage(image))

    fun removeRecord() = copy(voiceState = VoiceState.Recorder(false))

    fun addImage(image: MultisizedImage) = copy(media = media.addImage(image))
}