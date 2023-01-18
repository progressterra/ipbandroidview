package com.progressterra.ipbandroidview.ui.checklist

import android.Manifest
import androidx.lifecycle.ViewModel
import com.progressterra.ipbandroidview.R
import com.progressterra.ipbandroidview.composable.VoiceState
import com.progressterra.ipbandroidview.core.ScreenState
import com.progressterra.ipbandroidview.domain.usecase.CheckPermissionUseCase
import com.progressterra.ipbandroidview.domain.usecase.checklist.CheckMediaDetailsUseCase
import com.progressterra.ipbandroidview.domain.usecase.checklist.ChecklistUseCase
import com.progressterra.ipbandroidview.domain.usecase.checklist.CreateDocumentUseCase
import com.progressterra.ipbandroidview.domain.usecase.checklist.DocumentChecklistUseCase
import com.progressterra.ipbandroidview.domain.usecase.checklist.FetchExistingAuditUseCase
import com.progressterra.ipbandroidview.domain.usecase.checklist.FinishDocumentUseCase
import com.progressterra.ipbandroidview.domain.usecase.checklist.UpdateAnswerUseCase
import com.progressterra.ipbandroidview.domain.usecase.AskPermissionUseCase
import com.progressterra.ipbandroidview.domain.usecase.media.AudioProgressUseCase
import com.progressterra.ipbandroidview.domain.usecase.media.MakePhotoUseCase
import com.progressterra.ipbandroidview.domain.usecase.media.PauseAudioUseCase
import com.progressterra.ipbandroidview.domain.usecase.media.StartAudioUseCase
import com.progressterra.ipbandroidview.domain.usecase.media.StartRecordingUseCase
import com.progressterra.ipbandroidview.domain.usecase.media.StopRecordingUseCase
import com.progressterra.ipbandroidview.model.checklist.AuditDocument
import com.progressterra.ipbandroidview.model.checklist.Check
import com.progressterra.ipbandroidview.model.checklist.ChecklistStatus
import com.progressterra.ipbandroidview.model.media.MultisizedImage
import kotlinx.coroutines.delay
import org.orbitmvi.orbit.Container
import org.orbitmvi.orbit.ContainerHost
import org.orbitmvi.orbit.annotation.OrbitExperimental
import org.orbitmvi.orbit.syntax.simple.blockingIntent
import org.orbitmvi.orbit.syntax.simple.intent
import org.orbitmvi.orbit.syntax.simple.postSideEffect
import org.orbitmvi.orbit.syntax.simple.reduce
import org.orbitmvi.orbit.viewmodel.container

@OptIn(OrbitExperimental::class)
class ChecklistViewModel(
    private val createDocumentUseCase: CreateDocumentUseCase,
    private val finishDocumentUseCase: FinishDocumentUseCase,
    private val updateAnswerUseCase: UpdateAnswerUseCase,
    private val fetchExistingAuditUseCase: FetchExistingAuditUseCase,
    private val documentChecklistUseCase: DocumentChecklistUseCase,
    private val checklistUseCase: ChecklistUseCase,
    private val checkMediaDetailsUseCase: CheckMediaDetailsUseCase,
    private val startRecordingUseCase: StartRecordingUseCase,
    private val stopRecordingUseCase: StopRecordingUseCase,
    private val pauseAudioUseCase: PauseAudioUseCase,
    private val audioProgressUseCase: AudioProgressUseCase,
    private val startAudioUseCase: StartAudioUseCase,
    private val makePhotoUseCase: MakePhotoUseCase,
    private val askPermissionUseCase: AskPermissionUseCase,
    private val checkPermissionUseCase: CheckPermissionUseCase
) : ViewModel(), ContainerHost<ChecklistState, ChecklistEffect>, ChecklistInteractor {

    override val container: Container<ChecklistState, ChecklistEffect> = container(ChecklistState())

    private val micPermission = Manifest.permission.RECORD_AUDIO

    private val cameraPermission = Manifest.permission.CAMERA

    fun setDocument(auditDocument: AuditDocument, initialStatus: ChecklistStatus) = intent {
        reduce { ChecklistState(auditDocument = auditDocument, status = initialStatus) }
        refreshChecklist()
    }

    override fun refreshChecklist() = intent {
        reduce { state.updateChecklistScreenState(ScreenState.LOADING) }
        if (state.status != ChecklistStatus.CAN_BE_STARTED) documentChecklistUseCase(state.auditDocument.documentId!!).onSuccess { checks ->
            reduce { state.updateChecks(checks) }
            reduce { state.updateChecklistScreenState(ScreenState.SUCCESS) }
        }.onFailure { reduce { state.updateChecklistScreenState(ScreenState.ERROR) } }
        else checklistUseCase(state.auditDocument.checklistId).onSuccess { checks ->
            reduce { state.updateChecks(checks) }
            reduce { state.updateChecklistScreenState(ScreenState.SUCCESS) }
        }.onFailure { reduce { state.updateChecklistScreenState(ScreenState.ERROR) } }
    }

    override fun openCheck(check: Check) = intent {
        reduce { state.updateCurrentCheck(check) }
        refreshCheck()
    }

    override fun refreshCheck() = intent {
        reduce { state.updateCheckScreenState(ScreenState.LOADING) }
        checkMediaDetailsUseCase(state.currentCheck!!).onSuccess {
            reduce { state.updateCurrentCheckMedia(it) }
            reduce { state.updateCheckScreenState(ScreenState.SUCCESS) }
            if (it.voices.isNotEmpty())
                reduce { state.updateVoiceState(VoiceState.Player(false, 0f)) }
        }.onFailure { reduce { state.updateCheckScreenState(ScreenState.ERROR) } }
    }

    override fun onBack() = intent { postSideEffect(ChecklistEffect.Back) }

    override fun yesNo(yesNo: Boolean) =
        intent { reduce { state.updateCurrentCheckYesNo(yesNo = yesNo) } }

    override fun editCheckCommentary(commentary: String) = blockingIntent {
        reduce { state.updateCurrentCheckCommentary(comment = commentary) }
    }

    override fun startStopAudit() = intent {
        if (state.status == ChecklistStatus.ONGOING) {
            finishDocumentUseCase(state.auditDocument.documentId!!).onSuccess {
                reduce { state.updateStatus(ChecklistStatus.READ_ONLY) }
                postSideEffect(ChecklistEffect.Toast(R.string.audit_ended))
            }.onFailure {
                postSideEffect(ChecklistEffect.Toast(R.string.error_connection))
            }
        } else {
            fetchExistingAuditUseCase(
                state.auditDocument.placeId, state.auditDocument.checklistId
            ).onSuccess {
                reduce { state.updateAuditDocumentId(it) }
                reduce { state.updateStatus(ChecklistStatus.ONGOING) }
                postSideEffect(ChecklistEffect.Toast(R.string.audit_ongoing))
            }.onFailure {
                createDocumentUseCase(
                    state.auditDocument.checklistId, state.auditDocument.placeId
                ).onSuccess {
                    reduce { state.updateAuditDocumentId(it) }
                    reduce { state.updateStatus(ChecklistStatus.ONGOING) }
                    postSideEffect(ChecklistEffect.Toast(R.string.audit_started))
                }.onFailure {
                    postSideEffect(ChecklistEffect.Toast(R.string.error_connection))
                }
            }
            refreshChecklist()
        }
    }

    override fun startPausePlay() = intent {
        if (state.voiceState.ongoing) {
            pauseAudioUseCase()
            reduce {
                state.updateVoiceState(
                    VoiceState.Player(false, (state.voiceState as VoiceState.Player).progress)
                )
            }
        } else {
            startAudioUseCase(state.currentCheckMedia?.voices?.last()?.id!!)
            do {
                val result = audioProgressUseCase().onSuccess { progress ->
                    reduce { state.updateVoiceState(VoiceState.Player(true, progress)) }
                    if (progress >= 1f) {
                        reduce { state.updateVoiceState(VoiceState.Player(false, 0f)) }
                    }
                }
                if (result.isFailure) break
                else delay(250)
            } while (state.voiceState.ongoing)
        }
    }

    override fun startStopRecording() = intent {
        if (state.voiceState.ongoing) {
            stopRecordingUseCase()
            reduce { state.updateVoiceState(VoiceState.Player(false, 0f)) }
        } else {
            checkPermissionUseCase(micPermission).onSuccess {
                startRecordingUseCase().onSuccess { voice ->
                    reduce { state.updateVoiceState(VoiceState.Recorder(true)) }
                    reduce { state.addVoice(voice) }
                }
            }.onFailure { askPermissionUseCase(micPermission) }
        }
    }

    override fun remove() = intent { reduce { state.removeRecord() } }

    override fun applyCheck() = intent {
        updateAnswerUseCase(
            check = state.currentCheck!!,
            checkDetails = state.currentCheckMedia!!.createPatched(),
        ).onSuccess {
            reduce { state.updateCheck(it) }
            postSideEffect(ChecklistEffect.Toast(R.string.answer_done))
        }.onFailure {
            postSideEffect(ChecklistEffect.Toast(R.string.error_happend))
        }
    }

    fun removePhoto(picture: MultisizedImage) = intent { reduce { state.removeImage(picture) } }

    override fun openImage(image: MultisizedImage) = intent {
        postSideEffect(ChecklistEffect.OpenImage(image, state.status == ChecklistStatus.ONGOING))
    }

    override fun onCamera() = intent {
        checkPermissionUseCase(cameraPermission).onSuccess {
            makePhotoUseCase().onSuccess {
                reduce { state.addImage(it) }
            }.onFailure { askPermissionUseCase(cameraPermission) }
        }
    }
}