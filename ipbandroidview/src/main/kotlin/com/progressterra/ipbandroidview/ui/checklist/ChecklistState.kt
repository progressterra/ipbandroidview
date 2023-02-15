package com.progressterra.ipbandroidview.ui.checklist

import androidx.compose.runtime.Immutable
import com.progressterra.ipbandroidview.composable.VoiceState
import com.progressterra.ipbandroidview.core.ScreenState
import com.progressterra.ipbandroidview.ext.createStats
import com.progressterra.ipbandroidview.ext.replaceById
import com.progressterra.ipbandroidview.model.AuditDocument
import com.progressterra.ipbandroidview.model.Check
import com.progressterra.ipbandroidview.model.ChecklistStats
import com.progressterra.ipbandroidview.model.ChecklistStatus
import com.progressterra.ipbandroidview.model.MultisizedImage
import com.progressterra.ipbandroidview.model.Voice

@Immutable
data class ChecklistState(
    val currentCheck: Check? = null,
    val currentCheckMedia: CurrentCheckMedia? = null,
    val voiceState: VoiceState = VoiceState.Recorder(false),
    val stats: ChecklistStats = ChecklistStats(0, 0, 0, 0),
    val auditDocument: AuditDocument = AuditDocument(),
    val checks: List<Check> = emptyList(),
    val checkScreenState: ScreenState = ScreenState.LOADING,
    val checklistScreenState: ScreenState = ScreenState.LOADING,
    val status: ChecklistStatus = ChecklistStatus.READ_ONLY,
    val email: String = ""
) {

    fun updateCheck(check: Check): ChecklistState {
        val newChecks = checks.replaceById(check)
        return copy(
            checks = newChecks, stats = newChecks.createStats()
        )
    }

    fun updateChecks(checks: List<Check>): ChecklistState = copy(
        checks = checks,
        stats = checks.createStats()
    )

    fun updateCurrentCheck(check: Check): ChecklistState = copy(currentCheck = check)

    fun removeImage(image: MultisizedImage): ChecklistState = copy(
        currentCheckMedia = currentCheckMedia!!.removeImage(image)
    )

    fun addImage(image: MultisizedImage): ChecklistState = copy(
        currentCheckMedia = currentCheckMedia!!.addImage(image)
    )

    fun removeRecord(): ChecklistState = copy(
        voiceState = VoiceState.Recorder(false),
        currentCheckMedia = currentCheckMedia!!.removeRecord()
    )

    fun updateVoiceState(voiceState: VoiceState): ChecklistState = copy(voiceState = voiceState)

    fun addVoice(voice: Voice): ChecklistState = copy(
        currentCheckMedia = currentCheckMedia!!.addVoice(voice)
    )

    fun updateChecklistScreenState(state: ScreenState): ChecklistState =
        copy(checklistScreenState = state)

    fun updateCheckScreenState(state: ScreenState): ChecklistState = copy(checkScreenState = state)

    fun updateCurrentCheckMedia(currentCheckMedia: CurrentCheckMedia): ChecklistState = copy(
        currentCheckMedia = currentCheckMedia
    )

    fun updateCurrentCheckCommentary(comment: String): ChecklistState {
        val newCurrentCheck = currentCheck!!.updateComment(comment)
        return copy(currentCheck = newCurrentCheck)
    }

    fun updateCurrentCheckYesNo(yesNo: Boolean): ChecklistState {
        val newCurrentCheck = currentCheck!!.updateYesNo(yesNo)
        return copy(currentCheck = newCurrentCheck)
    }

    fun updateAuditDocumentId(documentId: String): ChecklistState {
        val newAuditDocument = auditDocument.updateDocumentId(documentId)
        return copy(auditDocument = newAuditDocument)
    }

    fun updateStatus(status: ChecklistStatus): ChecklistState = copy(status = status)
}