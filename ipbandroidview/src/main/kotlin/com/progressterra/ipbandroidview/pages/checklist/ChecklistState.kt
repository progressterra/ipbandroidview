package com.progressterra.ipbandroidview.pages.checklist

import androidx.compose.runtime.Immutable
import com.progressterra.ipbandroidview.entities.AuditDocument
import com.progressterra.ipbandroidview.entities.Check
import com.progressterra.ipbandroidview.entities.ChecklistStatus
import com.progressterra.ipbandroidview.entities.CurrentCheckMedia
import com.progressterra.ipbandroidview.entities.MultisizedImage
import com.progressterra.ipbandroidview.entities.Voice
import com.progressterra.ipbandroidview.features.voice.VoiceState
import com.progressterra.ipbandroidview.shared.replaceById
import com.progressterra.ipbandroidview.shared.ui.button.ButtonState
import com.progressterra.ipbandroidview.shared.ui.statecolumn.ScreenState
import com.progressterra.ipbandroidview.shared.ui.statecolumn.StateColumnState

@Immutable
data class ChecklistState(
    val auditDocument: AuditDocument = AuditDocument(),
    val checks: List<Check> = emptyList(),
    val screen: StateColumnState = StateColumnState(id = "main"),
    val currentCheckState: CurrentCheckState = CurrentCheckState(),
    val startButton: ButtonState = ButtonState(id = "start"),
    val finishButton: ButtonState = ButtonState(id = "finish"),
    val sendButton: ButtonState = ButtonState(id = "send"),
    val status: ChecklistStatus = ChecklistStatus.READ_ONLY
) {

    fun updateReadyAvailable(available: Boolean) =
        copy(currentCheckState = currentCheckState.updateReadyAvailable(available))

    fun addVoice(voice: Voice) = copy(currentCheckState = currentCheckState.addVoice(voice))

    fun updateDocumentId(documentId: String) =
        copy(auditDocument = auditDocument.copy(documentId = documentId))

    fun availabilityStartButton(availability: Boolean) =
        copy(startButton = startButton.copy(enabled = availability))

    fun availabilityFinishButton(availability: Boolean) =
        copy(finishButton = finishButton.copy(enabled = availability))

    fun updateCurrentCheck(check: Check) =
        copy(currentCheckState = currentCheckState.updateCheck(check))

    fun updateYesNo(yesNo: Boolean) = copy(currentCheckState = currentCheckState.updateYesNo(yesNo))

    fun updateMedia(media: CurrentCheckMedia) =
        copy(currentCheckState = currentCheckState.updateMedia(media))

    fun updateCheckScreenState(screenState: ScreenState) =
        copy(currentCheckState = currentCheckState.updateScreenState(screenState))

    fun updateVoiceState(voiceState: VoiceState) =
        copy(currentCheckState = currentCheckState.updateVoiceState(voiceState))

    fun removeImage(image: MultisizedImage) =
        copy(currentCheckState = currentCheckState.removeImage(image))

    fun updateAuditDocument(auditDocument: AuditDocument) = copy(auditDocument = auditDocument)

    fun updateStatus(status: ChecklistStatus) =
        copy(currentCheckState = currentCheckState.updateStatus(status), status = status)

    fun updateScreenState(screenState: ScreenState) = copy(screen = screen.copy(state = screenState))

    fun updateCheck(check: Check) = copy(checks = checks.replaceById(check))

    fun updateChecks(checks: List<Check>) = copy(checks = checks)

    fun updateCommentAvailability(availability: Boolean) =
        copy(currentCheckState = currentCheckState.updateCommentAvailable(availability))

    fun updateComment(comment: String) =
        copy(currentCheckState = currentCheckState.updateComment(comment))

    fun removeRecord() = copy(currentCheckState = currentCheckState.removeRecord())

    fun addImage(image: MultisizedImage) =
        copy(currentCheckState = currentCheckState.addImage(image))
}