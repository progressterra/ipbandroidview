package com.progressterra.ipbandroidview.ui.checklist

import android.Manifest
import androidx.lifecycle.ViewModel
import com.progressterra.ipbandroidview.R
import com.progressterra.ipbandroidview.composable.VoiceState
import com.progressterra.ipbandroidview.composable.component.ButtonEvent
import com.progressterra.ipbandroidview.composable.component.ButtonState
import com.progressterra.ipbandroidview.composable.component.TextFieldEvent
import com.progressterra.ipbandroidview.composable.component.TextFieldState
import com.progressterra.ipbandroidview.core.ManageResources
import com.progressterra.ipbandroidview.core.ScreenState
import com.progressterra.ipbandroidview.domain.exception.NoEmailException
import com.progressterra.ipbandroidview.domain.usecase.AskPermissionUseCase
import com.progressterra.ipbandroidview.domain.usecase.CheckPermissionUseCase
import com.progressterra.ipbandroidview.domain.usecase.checklist.CheckMediaDetailsUseCase
import com.progressterra.ipbandroidview.domain.usecase.checklist.ChecklistUseCase
import com.progressterra.ipbandroidview.domain.usecase.checklist.CreateDocumentUseCase
import com.progressterra.ipbandroidview.domain.usecase.checklist.DocumentChecklistUseCase
import com.progressterra.ipbandroidview.domain.usecase.checklist.FetchExistingAuditUseCase
import com.progressterra.ipbandroidview.domain.usecase.checklist.FinishDocumentUseCase
import com.progressterra.ipbandroidview.domain.usecase.checklist.SendResultOnEmailUseCase
import com.progressterra.ipbandroidview.domain.usecase.checklist.UpdateAnswerUseCase
import com.progressterra.ipbandroidview.domain.usecase.media.AudioProgressUseCase
import com.progressterra.ipbandroidview.domain.usecase.media.MakePhotoUseCase
import com.progressterra.ipbandroidview.domain.usecase.media.PauseAudioUseCase
import com.progressterra.ipbandroidview.domain.usecase.media.StartAudioUseCase
import com.progressterra.ipbandroidview.domain.usecase.media.StartRecordingUseCase
import com.progressterra.ipbandroidview.domain.usecase.media.StopRecordingUseCase
import com.progressterra.ipbandroidview.ext.toScreenState
import com.progressterra.ipbandroidview.model.AuditDocument
import com.progressterra.ipbandroidview.model.Check
import com.progressterra.ipbandroidview.model.ChecklistStatus
import com.progressterra.ipbandroidview.model.MultisizedImage
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
    private val checkPermissionUseCase: CheckPermissionUseCase,
    private val sendResultOnEmailUseCase: SendResultOnEmailUseCase,
    manageResources: ManageResources
) : ViewModel(), ContainerHost<ChecklistState, ChecklistEffect>, ChecklistInteractor {

    override val container: Container<ChecklistState, ChecklistEffect> = container(
        ChecklistState(
            finishButton = ButtonState(
                text = manageResources.string(R.string.end_audit)
            ),
            startButton = ButtonState(
                text = manageResources.string(R.string.start_audit)
            ),
            currentCheckState = CurrentCheckState(
                comment = TextFieldState(
                    hint = manageResources.string(R.string.comment)
                ),
                ready = ButtonState(
                    text = manageResources.string(R.string.ready)
                )
            ),
            sendButton = ButtonState(
                text = manageResources.string(R.string.send_on_email)
            )
        )
    )

    private val micPermission = Manifest.permission.RECORD_AUDIO

    private val cameraPermission = Manifest.permission.CAMERA

    fun setDocument(auditDocument: AuditDocument, initialStatus: ChecklistStatus) = intent {
        reduce { state.updateAuditDocument(auditDocument) }
        updateStatus(initialStatus)
        refreshChecklist()
    }

    fun removePhoto(picture: MultisizedImage) = intent {
        reduce { state.removeImage(picture) }
    }

    override fun refreshChecklist() = intent {
        reduce { state.copy(screenState = ScreenState.LOADING) }
        var newChecks: List<Check> = emptyList()
        var isSuccess = true
        if (!state.status.isCanBeStarted()) documentChecklistUseCase(
            state.auditDocument.documentId!!
        ).onSuccess { newChecks = it }.onFailure { isSuccess = false }
        else checklistUseCase(state.auditDocument.checklistId).onSuccess { newChecks = it }
            .onFailure { isSuccess = false }
        reduce { state.updateChecks(newChecks).updateScreenState(isSuccess.toScreenState()) }
    }

    override fun openCheck(check: Check) = intent {
        reduce { state.updateCurrentCheck(check) }
        refreshCheck()
        onYesNoUpdate()
    }

    override fun onBack() = intent { postSideEffect(ChecklistEffect.Back) }

    override fun handleEvent(id: String, event: ButtonEvent) = intent {
        when (id) {
            "finish" -> when (event) {
                is ButtonEvent.Click -> {
                    reduce { state.availabilityFinishButton(false) }
                    finishDocumentUseCase(state.auditDocument.documentId!!).onSuccess {
                        updateStatus(ChecklistStatus.READ_ONLY)
                        postSideEffect(ChecklistEffect.Toast(R.string.audit_ended))
                    }.onFailure {
                        postSideEffect(ChecklistEffect.Toast(R.string.error_connection))
                    }
                    reduce { state.availabilityFinishButton(true) }
                }
            }
            "start" -> when (event) {
                is ButtonEvent.Click -> {
                    reduce { state.availabilityStartButton(false) }
                    fetchExistingAuditUseCase(
                        state.auditDocument.placeId, state.auditDocument.checklistId
                    ).onSuccess {
                        reduce { state.updateDocumentId(it) }
                        updateStatus(ChecklistStatus.ONGOING)
                        postSideEffect(ChecklistEffect.Toast(R.string.audit_ongoing))
                    }.onFailure {
                        createDocumentUseCase(
                            state.auditDocument.checklistId, state.auditDocument.placeId
                        ).onSuccess {
                            reduce { state.updateDocumentId(it) }
                            updateStatus(ChecklistStatus.ONGOING)
                            postSideEffect(ChecklistEffect.Toast(R.string.audit_started))
                        }
                            .onFailure { postSideEffect(ChecklistEffect.Toast(R.string.error_connection)) }
                    }
                    reduce {
                        state.availabilityStartButton(true)
                    }
                    refreshChecklist()
                }
            }
            "ready" -> when (event) {
                is ButtonEvent.Click -> {
                    updateAnswerUseCase(
                        check = state.currentCheckState.check,
                        checkDetails = state.currentCheckState.media.createPatched(),
                    ).onSuccess {
                        reduce { state.updateCheck(it) }
                        postSideEffect(ChecklistEffect.Toast(R.string.answer_done))
                    }.onFailure {
                        postSideEffect(ChecklistEffect.Toast(R.string.error_happend))
                    }
                }
            }
            "send" -> when (event) {
                is ButtonEvent.Click -> {
                    sendResultOnEmailUseCase(state.auditDocument.documentId!!).onSuccess {
                        postSideEffect(ChecklistEffect.Toast(R.string.email_sent))
                    }.onFailure {
                        if (it is NoEmailException) {
                            postSideEffect(ChecklistEffect.Toast(R.string.no_email))
                            postSideEffect(ChecklistEffect.AddEmail)
                        } else
                            postSideEffect(ChecklistEffect.Toast(R.string.email_not_sent))
                    }
                }
            }
        }
    }

    override fun handleEvent(id: String, event: TextFieldEvent) = blockingIntent {
        when (id) {
            "commentary" -> when (event) {
                is TextFieldEvent.TextChanged -> reduce { state.updateComment(event.text) }
                is TextFieldEvent.Action -> Unit
            }
        }
    }

    override fun handleEvent(id: String, event: CurrentCheckEvent) = intent {
        when (id) {
            "main" -> when (event) {
                is CurrentCheckEvent.OpenCamera -> {
                    checkPermissionUseCase(cameraPermission).onSuccess {
                        makePhotoUseCase().onSuccess { reduce { state.addImage(it) } }
                    }.onFailure {
                        askPermissionUseCase(cameraPermission)
                    }
                }
                is CurrentCheckEvent.OpenImage -> postSideEffect(
                    ChecklistEffect.OpenImage(
                        event.image,
                        state.status.isOngoing()
                    )
                )
                is CurrentCheckEvent.Refresh -> refreshCheck()
                is CurrentCheckEvent.RemoveVoice -> reduce { state.removeRecord() }
                is CurrentCheckEvent.StartPausePlay -> {
                    if (state.currentCheckState.voiceState.ongoing) {
                        pauseAudioUseCase()
                        reduce {
                            state.updateVoiceState(
                                VoiceState.Player(
                                    false,
                                    (state.currentCheckState.voiceState as VoiceState.Player).progress
                                )
                            )
                        }
                    } else {
                        startAudioUseCase(state.currentCheckState.media.voices.last().id)
                        do {
                            val result = audioProgressUseCase().onSuccess { progress ->
                                if (progress >= 1f) reduce {
                                    state.updateVoiceState(VoiceState.Player(false, 0f))
                                }
                                else reduce {
                                    state.updateVoiceState(VoiceState.Player(true, progress))
                                }
                            }
                            if (result.isFailure) break
                            else delay(250)
                        } while (state.currentCheckState.voiceState.ongoing)
                    }
                }
                is CurrentCheckEvent.StartStopRecording -> {
                    if (state.currentCheckState.voiceState.ongoing) {
                        stopRecordingUseCase()
                        reduce { state.updateVoiceState(VoiceState.Player(false, 0f)) }
                    } else {
                        checkPermissionUseCase(micPermission).onSuccess {
                            startRecordingUseCase().onSuccess { voice ->
                                reduce {
                                    state.addVoice(voice)
                                        .updateVoiceState(VoiceState.Recorder(true))
                                }
                            }
                        }.onFailure { askPermissionUseCase(micPermission) }
                    }
                }
                is CurrentCheckEvent.YesNo -> {
                    reduce { state.updateYesNo(event.yesNo) }
                    onYesNoUpdate()
                }
            }
        }
    }

    private fun updateStatus(status: ChecklistStatus) = intent {
        reduce { state.updateStatus(status).updateCommentAvailability(status.isOngoing()) }
    }

    private fun onYesNoUpdate() = intent {
        reduce { state.updateReadyAvailable(state.currentCheckState.check.yesNo != null) }
    }

    private fun refreshCheck() = intent {
        reduce { state.updateCheckScreenState(ScreenState.LOADING) }
        checkMediaDetailsUseCase(state.currentCheckState.check).onSuccess {
            reduce { state.updateMedia(it).updateCheckScreenState(ScreenState.SUCCESS) }
            if (it.voices.isNotEmpty()) reduce {
                state.updateVoiceState(VoiceState.Player(false, 0f))
            } else reduce { state.updateVoiceState(VoiceState.Recorder(false)) }
        }.onFailure { reduce { state.updateCheckScreenState(ScreenState.ERROR) } }
    }
}