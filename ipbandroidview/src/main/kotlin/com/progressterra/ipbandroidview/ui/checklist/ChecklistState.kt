package com.progressterra.ipbandroidview.ui.checklist

import androidx.compose.runtime.Immutable
import com.progressterra.ipbandroidview.composable.VoiceState
import com.progressterra.ipbandroidview.composable.component.ButtonState
import com.progressterra.ipbandroidview.core.ScreenState
import com.progressterra.ipbandroidview.ext.replaceById
import com.progressterra.ipbandroidview.model.AuditDocument
import com.progressterra.ipbandroidview.model.Check
import com.progressterra.ipbandroidview.model.ChecklistStatus
import com.progressterra.ipbandroidview.model.MultisizedImage
import com.progressterra.ipbandroidview.model.Voice

@Immutable
data class ChecklistState(
    val auditDocument: AuditDocument = AuditDocument(),
    val checks: List<Check> = emptyList(),
    val screenState: ScreenState = ScreenState.LOADING,
    val currentCheckState: CurrentCheckState = CurrentCheckState(),
    val startButton: ButtonState = ButtonState(),
    val finishButton: ButtonState = ButtonState(),
    val sendButton: ButtonState = ButtonState(),
    val status: ChecklistStatus = ChecklistStatus.READ_ONLY
) {

    fun updateReadyAvailable(available: Boolean) =
        copy(currentCheckState = currentCheckState.updateReadyAvailable(available))

    fun addVoice(voice: Voice) = copy(currentCheckState = currentCheckState.addVoice(voice))

    fun updateDocumentId(documentId: String) =
        copy(auditDocument = auditDocument.copy(documentId = documentId))

    fun availabilityStartButton(availability: Boolean) =
        copy(startButton = startButton.updateEnabled(availability))

    fun availabilityFinishButton(availability: Boolean) =
        copy(finishButton = finishButton.updateEnabled(availability))

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

    fun updateScreenState(screenState: ScreenState) = copy(screenState = screenState)

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