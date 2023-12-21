package com.progressterra.ipbandroidview.pages.checklist

import android.Manifest
import com.progressterra.ipbandroidview.entities.AuditDocument
import com.progressterra.ipbandroidview.entities.Check
import com.progressterra.ipbandroidview.entities.ChecklistStatus
import com.progressterra.ipbandroidview.entities.MultisizedImage
import com.progressterra.ipbandroidview.entities.toScreenState
import com.progressterra.ipbandroidview.features.topbar.TopBarEvent
import com.progressterra.ipbandroidview.features.voice.VoiceState
import com.progressterra.ipbandroidview.processes.checklist.CheckMediaDetailsUseCase
import com.progressterra.ipbandroidview.processes.checklist.ChecklistNonPagingUseCase
import com.progressterra.ipbandroidview.processes.checklist.CreateDocumentUseCase
import com.progressterra.ipbandroidview.processes.checklist.DocumentChecklistUseCase
import com.progressterra.ipbandroidview.processes.checklist.FetchExistingAuditUseCase
import com.progressterra.ipbandroidview.processes.checklist.FinishDocumentUseCase
import com.progressterra.ipbandroidview.processes.checklist.SendResultOnEmailUseCase
import com.progressterra.ipbandroidview.processes.checklist.UpdateAnswerUseCase
import com.progressterra.ipbandroidview.processes.media.AudioProgressUseCase
import com.progressterra.ipbandroidview.processes.media.MakePhotoUseCase
import com.progressterra.ipbandroidview.processes.media.PauseAudioUseCase
import com.progressterra.ipbandroidview.processes.media.StartAudioUseCase
import com.progressterra.ipbandroidview.processes.media.StartRecordingUseCase
import com.progressterra.ipbandroidview.processes.media.StopRecordingUseCase
import com.progressterra.ipbandroidview.processes.permission.AskPermissionUseCase
import com.progressterra.ipbandroidview.processes.permission.CheckPermissionUseCase
import com.progressterra.ipbandroidview.shared.mvi.AbstractInputViewModel
import com.progressterra.ipbandroidview.shared.ui.button.ButtonEvent
import com.progressterra.ipbandroidview.shared.ui.statecolumn.ScreenState
import com.progressterra.ipbandroidview.shared.ui.statecolumn.StateColumnEvent
import com.progressterra.ipbandroidview.shared.ui.textfield.TextFieldEvent
import kotlinx.coroutines.delay

class ChecklistViewModel(
    private val createDocumentUseCase: CreateDocumentUseCase,
    private val finishDocumentUseCase: FinishDocumentUseCase,
    private val updateAnswerUseCase: UpdateAnswerUseCase,
    private val fetchExistingAuditUseCase: FetchExistingAuditUseCase,
    private val documentChecklistUseCase: DocumentChecklistUseCase,
    private val checklistUseCase: ChecklistNonPagingUseCase,
    private val checkMediaDetailsUseCase: CheckMediaDetailsUseCase,
    private val startRecordingUseCase: StartRecordingUseCase,
    private val stopRecordingUseCase: StopRecordingUseCase,
    private val pauseAudioUseCase: PauseAudioUseCase,
    private val audioProgressUseCase: AudioProgressUseCase,
    private val startAudioUseCase: StartAudioUseCase,
    private val makePhotoUseCase: MakePhotoUseCase,
    private val askPermissionUseCase: AskPermissionUseCase,
    private val checkPermissionUseCase: CheckPermissionUseCase,
    private val sendResultOnEmailUseCase: SendResultOnEmailUseCase
) : AbstractInputViewModel<Pair<AuditDocument, ChecklistStatus>, ChecklistState, ChecklistEffect>(),
    UseChecklistScreen {

    override fun createInitialState() = ChecklistState()

    private val micPermission = Manifest.permission.RECORD_AUDIO

    override fun setup(data: Pair<AuditDocument, ChecklistStatus>) {
        onBackground {
            emitState { it.updateAuditDocument(data.first) }
            updateStatus(data.second)
            refreshChecklist()
        }
    }

    fun removePhoto(picture: MultisizedImage) {
        onBackground {
            emitState { it.removeImage(picture) }
        }
    }

    override fun handle(event: TopBarEvent) {
        postEffect(ChecklistEffect.OnBack)
    }

    private fun refreshCheck() {
        onBackground {
            emitState { it.updateCheckScreenState(ScreenState.LOADING) }
            checkMediaDetailsUseCase(currentState.currentCheckState.check).onSuccess { media ->
                emitState {
                    it.updateMedia(media)
                        .updateComment(currentState.currentCheckState.check.comment)
                        .updateCheckScreenState(ScreenState.SUCCESS)
                }
                if (media.voices.isNotEmpty()) {
                    emitState {
                        it.updateVoiceState(VoiceState.Player(false, 0f))
                    }
                } else {
                    emitState { it.updateVoiceState(VoiceState.Recorder(false)) }
                }
            }.onFailure { emitState { it.updateCheckScreenState(ScreenState.ERROR) } }
        }
    }

    private fun refreshChecklist() {
        onBackground {
            emitState { it.copy(screen = it.screen.copy(state = ScreenState.LOADING)) }
            var newChecks: List<Check> = emptyList()
            var isSuccess = true
            if (!currentState.status.isCanBeStarted()) documentChecklistUseCase(
                currentState.auditDocument.documentId!!
            ).onSuccess { newChecks = it }.onFailure { isSuccess = false }
            else checklistUseCase(currentState.auditDocument.checklistId).onSuccess { list ->
                newChecks = list
            }
                .onFailure { isSuccess = false }
            emitState { it.updateChecks(newChecks).updateScreenState(isSuccess.toScreenState()) }
        }
    }

    override fun handle(event: StateColumnEvent) {
        onBackground {
            if (event.id == "dialog") {
                refreshCheck()
            } else if (event.id == "main") {
                refreshChecklist()
            }
        }
    }

    override fun handle(event: ChecklistEvent) {
        onBackground {

            when (event) {
                is ChecklistEvent.OnCheck -> {
                    emitState { it.updateCurrentCheck(event.check) }
                    refreshCheck()
                    onYesNoUpdate()
                }

                is ChecklistEvent.OnImage -> ChecklistEffect.OnImage(
                    event.image,
                    currentState.status.isOngoing()
                )


                is ChecklistEvent.OpenCamera -> makePhotoUseCase().onSuccess { image ->
                    emitState { it.addImage(image) }
                }

                is ChecklistEvent.RemoveVoice -> {
                    emitState { it.removeRecord() }
                }

                is ChecklistEvent.StartPausePlay -> if (currentState.currentCheckState.voiceState.ongoing) {
                    pauseAudioUseCase()
                    emitState {
                        it.updateVoiceState(
                            VoiceState.Player(
                                false,
                                (it.currentCheckState.voiceState as VoiceState.Player).progress
                            )
                        )
                    }
                } else {
                    startAudioUseCase(currentState.currentCheckState.media.voices.last().id)
                    do {
                        val result = audioProgressUseCase().onSuccess { progress ->
                            if (progress >= 1f) emitState {
                                it.updateVoiceState(VoiceState.Player(false, 0f))
                            }
                            else emitState {
                                it.updateVoiceState(
                                    VoiceState.Player(
                                        true,
                                        progress
                                    )
                                )
                            }
                        }
                        if (result.isFailure) break
                        else delay(250)
                    } while (currentState.currentCheckState.voiceState.ongoing)
                }

                is ChecklistEvent.StartStopRecording -> if (currentState.currentCheckState.voiceState.ongoing) {
                    stopRecordingUseCase()
                    emitState { it.updateVoiceState(VoiceState.Player(false, 0f)) }
                } else {
                    checkPermissionUseCase(micPermission).onSuccess {
                        startRecordingUseCase().onSuccess { voice ->
                            emitState {
                                it.addVoice(voice).updateVoiceState(VoiceState.Recorder(true))
                            }
                        }
                    }.onFailure { askPermissionUseCase(micPermission) }
                }

                is ChecklistEvent.YesNo -> {
                    emitState { it.updateYesNo(event.yesNo) }
                    onYesNoUpdate()
                }
            }
        }
    }

    override fun handle(event: ButtonEvent) {
        onBackground {
            when (event.id) {
                "finish" -> {
                    emitState { it.availabilityFinishButton(false) }
                    finishDocumentUseCase(currentState.auditDocument.documentId!!).onSuccess {
                        updateStatus(ChecklistStatus.READ_ONLY)
                    }
                    emitState { it.availabilityFinishButton(true) }
                }

                "start" -> {
                    emitState { it.availabilityStartButton(false) }
                    fetchExistingAuditUseCase(
                        currentState.auditDocument.placeId, currentState.auditDocument.checklistId
                    ).onSuccess { id ->
                        emitState { it.updateDocumentId(id) }
                        updateStatus(ChecklistStatus.ONGOING)
                    }.onFailure {
                        createDocumentUseCase(
                            currentState.auditDocument.checklistId,
                            currentState.auditDocument.placeId
                        ).onSuccess { id ->
                            emitState { it.updateDocumentId(id) }
                            updateStatus(ChecklistStatus.ONGOING)
                        }
                    }
                    emitState {
                        it.availabilityStartButton(true)
                    }
                    refreshChecklist()
                }

                "ready" -> updateAnswerUseCase(
                    check = currentState.currentCheckState.check,
                    checkDetails = currentState.currentCheckState.media.createPatched(),
                ).onSuccess { id ->
                    emitState { it.updateCheck(id) }
                }

                "send" -> sendResultOnEmailUseCase(currentState.auditDocument.documentId!!)

            }
        }
    }

    override fun handle(event: TextFieldEvent) {
        when (event) {
            is TextFieldEvent.TextChanged -> emitState { it.updateComment(event.text) }
            is TextFieldEvent.Action -> Unit
            is TextFieldEvent.AdditionalAction -> Unit
        }
    }


    private fun updateStatus(status: ChecklistStatus) {
        onBackground {
            emitState { it.updateStatus(status).updateCommentAvailability(status.isOngoing()) }
        }
    }


    private fun onYesNoUpdate() {
        onBackground {
            emitState { it.updateReadyAvailable(currentState.currentCheckState.check.yesNo != null) }
        }
    }
}