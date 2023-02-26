package com.progressterra.ipbandroidview.ui.checklist

import android.Manifest
import android.util.Log
import androidx.lifecycle.ViewModel
import com.progressterra.ipbandroidview.R
import com.progressterra.ipbandroidview.composable.VoiceState
import com.progressterra.ipbandroidview.composable.component.ButtonEvent
import com.progressterra.ipbandroidview.composable.component.ButtonState
import com.progressterra.ipbandroidview.composable.component.TextFieldEvent
import com.progressterra.ipbandroidview.composable.component.TextFieldState
import com.progressterra.ipbandroidview.core.ManageResources
import com.progressterra.ipbandroidview.core.ScreenState
import com.progressterra.ipbandroidview.domain.usecase.AskPermissionUseCase
import com.progressterra.ipbandroidview.domain.usecase.CheckPermissionUseCase
import com.progressterra.ipbandroidview.domain.usecase.checklist.CheckMediaDetailsUseCase
import com.progressterra.ipbandroidview.domain.usecase.checklist.ChecklistUseCase
import com.progressterra.ipbandroidview.domain.usecase.checklist.CreateDocumentUseCase
import com.progressterra.ipbandroidview.domain.usecase.checklist.DocumentChecklistUseCase
import com.progressterra.ipbandroidview.domain.usecase.checklist.FetchExistingAuditUseCase
import com.progressterra.ipbandroidview.domain.usecase.checklist.FinishDocumentUseCase
import com.progressterra.ipbandroidview.domain.usecase.checklist.UpdateAnswerUseCase
import com.progressterra.ipbandroidview.domain.usecase.media.AudioProgressUseCase
import com.progressterra.ipbandroidview.domain.usecase.media.MakePhotoUseCase
import com.progressterra.ipbandroidview.domain.usecase.media.PauseAudioUseCase
import com.progressterra.ipbandroidview.domain.usecase.media.StartAudioUseCase
import com.progressterra.ipbandroidview.domain.usecase.media.StartRecordingUseCase
import com.progressterra.ipbandroidview.domain.usecase.media.StopRecordingUseCase
import com.progressterra.ipbandroidview.ext.replaceById
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
                ),
            ),
        )
    )

    private val micPermission = Manifest.permission.RECORD_AUDIO

    private val cameraPermission = Manifest.permission.CAMERA

    fun setDocument(auditDocument: AuditDocument, initialStatus: ChecklistStatus) = intent {
        reduce {
            val newCurrentCheckState = state.currentCheckState.copy(status = initialStatus)
            state.copy(auditDocument = auditDocument, currentCheckState = newCurrentCheckState)
        }
        refreshChecklist()
    }

    override fun refreshChecklist() = intent {
        reduce { state.copy(screenState = ScreenState.LOADING) }
        var newChecks: List<Check> = emptyList()
        var newState: ScreenState = ScreenState.ERROR
        if (state.currentCheckState.status != ChecklistStatus.CAN_BE_STARTED) documentChecklistUseCase(
            state.auditDocument.documentId!!
        ).onSuccess { checks ->
            newChecks = checks
            newState = ScreenState.SUCCESS
        }
        else checklistUseCase(state.auditDocument.checklistId).onSuccess { checks ->
            newChecks = checks
            newState = ScreenState.SUCCESS
        }
        reduce { state.copy(checks = newChecks, screenState = newState) }
    }

    override fun openCheck(check: Check) = intent {
        reduce {
            val newCurrentCheckState = state.currentCheckState.copy(check = check)
            state.copy(currentCheckState = newCurrentCheckState)
        }
        refreshCheck()
    }

    private fun refreshCheck() = intent {
        reduce {
            val newCurrentCheckState =
                state.currentCheckState.copy(screenState = ScreenState.LOADING)
            state.copy(currentCheckState = newCurrentCheckState)

        }
        checkMediaDetailsUseCase(state.currentCheckState.check).onSuccess {
            reduce {
                val newCurrentCheckState = state.currentCheckState.copy(
                    screenState = ScreenState.SUCCESS,
                    media = it
                )
                state.copy(currentCheckState = newCurrentCheckState)
            }
            if (it.voices.isNotEmpty()) reduce {
                val newCurrentCheckState = state.currentCheckState.copy(
                    voiceState = VoiceState.Player(false, 0f)
                )
                state.copy(currentCheckState = newCurrentCheckState)
            } else reduce {
                val newCurrentCheckState = state.currentCheckState.copy(
                    voiceState = VoiceState.Recorder(false)
                )
                state.copy(currentCheckState = newCurrentCheckState)
            }
        }.onFailure {
            reduce {
                val newCurrentCheckState =
                    state.currentCheckState.copy(screenState = ScreenState.ERROR)
                state.copy(currentCheckState = newCurrentCheckState)
            }
        }
    }

    override fun onBack() = intent { postSideEffect(ChecklistEffect.Back) }

    private fun yesNo(yesNo: Boolean) =
        intent {
            reduce {
                val newCurrentCheck = state.currentCheckState.check.copy(yesNo = yesNo)
                val newCurrentCheckState = state.currentCheckState.copy(check = newCurrentCheck)
                state.copy(currentCheckState = newCurrentCheckState)
            }
        }

    private fun startPausePlay() = intent {
        if (state.currentCheckState.voiceState.ongoing) {
            pauseAudioUseCase()
            reduce {
                val newCurrentCheckState = state.currentCheckState.copy(
                    voiceState = VoiceState.Player(
                        false,
                        (state.currentCheckState.voiceState as VoiceState.Player).progress
                    )
                )
                state.copy(currentCheckState = newCurrentCheckState)
            }
        } else {
            startAudioUseCase(state.currentCheckState.media.voices.last().id)
            do {
                val result = audioProgressUseCase().onSuccess { progress ->
                    if (progress >= 1f) reduce {
                        val newCurrentCheckState = state.currentCheckState.copy(
                            voiceState = VoiceState.Player(false, 0f)
                        )
                        state.copy(currentCheckState = newCurrentCheckState)
                    }
                    else reduce {
                        val newCurrentCheckState = state.currentCheckState.copy(
                            voiceState = VoiceState.Player(true, progress)
                        )
                        state.copy(currentCheckState = newCurrentCheckState)
                    }
                }
                if (result.isFailure) break
                else delay(250)
            } while (state.currentCheckState.voiceState.ongoing)
        }
    }

    private fun startStopRecording() = intent {
        if (state.currentCheckState.voiceState.ongoing) {
            stopRecordingUseCase()
            reduce {
                val newCurrentCheckState = state.currentCheckState.copy(
                    voiceState = VoiceState.Player(false, 0f)
                )
                state.copy(currentCheckState = newCurrentCheckState)
            }
        } else {
            checkPermissionUseCase(micPermission).onSuccess {
                startRecordingUseCase().onSuccess { voice ->
                    reduce {
                        val newCurrentCheckMedia = state.currentCheckState.media.addVoice(voice)
                        val newCurrentCheckState = state.currentCheckState.copy(
                            voiceState = VoiceState.Recorder(true),
                            media = newCurrentCheckMedia
                        )
                        state.copy(currentCheckState = newCurrentCheckState)
                    }
                }
            }.onFailure { askPermissionUseCase(micPermission) }
        }
    }

    private fun removeRecord() = intent {
        reduce {
            val newCurrentCheckMedia = state.currentCheckState.media.removeRecord()
            val newCurrentCheckState = state.currentCheckState.copy(media = newCurrentCheckMedia)
            state.copy(currentCheckState = newCurrentCheckState)
        }
    }

    fun removePhoto(picture: MultisizedImage) = intent {
        reduce {
            val newCurrentCheckMedia = state.currentCheckState.media.removeImage(picture)
            val newCurrentCheckState = state.currentCheckState.copy(media = newCurrentCheckMedia)
            state.copy(currentCheckState = newCurrentCheckState)
        }
    }

    private fun openImage(image: MultisizedImage) = intent {
        postSideEffect(ChecklistEffect.OpenImage(image, state.currentCheckState.status.isOngoing()))
    }

    private fun openCamera() = intent {
        checkPermissionUseCase(cameraPermission).onSuccess {
            Log.d("Camera", "onCamera: permission granted")
            makePhotoUseCase().onSuccess {
                reduce {
                    val newCurrentCheckMedia = state.currentCheckState.media.addImage(it)
                    val newCurrentCheckState =
                        state.currentCheckState.copy(media = newCurrentCheckMedia)
                    state.copy(currentCheckState = newCurrentCheckState)
                }
            }.onFailure {
                Log.d("Camera", "onCamera: $it")
                askPermissionUseCase(cameraPermission)
            }
        }
    }

    override fun handleEvent(id: String, event: ButtonEvent) = intent {
        when (id) {
            "finish" -> {
                reduce {
                    val newFinish = state.finishButton.updateEnabled(false)
                    state.copy(finishButton = newFinish)
                }
                finishDocumentUseCase(state.auditDocument.documentId!!).onSuccess {
                    reduce {
                        val newCurrentCheckState =
                            state.currentCheckState.copy(status = ChecklistStatus.READ_ONLY)
                        state.copy(currentCheckState = newCurrentCheckState)
                    }
                    postSideEffect(ChecklistEffect.Toast(R.string.audit_ended))
                }.onFailure {
                    postSideEffect(ChecklistEffect.Toast(R.string.error_connection))
                }
                reduce {
                    val newFinish = state.finishButton.updateEnabled(true)
                    state.copy(finishButton = newFinish)
                }
            }
            "start" -> {
                reduce {
                    val newStart = state.startButton.updateEnabled(false)
                    state.copy(startButton = newStart)
                }
                fetchExistingAuditUseCase(
                    state.auditDocument.placeId, state.auditDocument.checklistId
                ).onSuccess {
                    reduce {
                        val newCurrentCheckState =
                            state.currentCheckState.copy(status = ChecklistStatus.ONGOING)
                        val newAuditDocument = state.auditDocument.copy(documentId = it)
                        state.copy(
                            currentCheckState = newCurrentCheckState,
                            auditDocument = newAuditDocument
                        )
                    }
                    postSideEffect(ChecklistEffect.Toast(R.string.audit_ongoing))
                }.onFailure {
                    createDocumentUseCase(
                        state.auditDocument.checklistId, state.auditDocument.placeId
                    ).onSuccess {
                        reduce {
                            val newCurrentCheckState =
                                state.currentCheckState.copy(status = ChecklistStatus.ONGOING)
                            val newAuditDocument = state.auditDocument.copy(documentId = it)
                            state.copy(
                                currentCheckState = newCurrentCheckState,
                                auditDocument = newAuditDocument
                            )
                        }
                        postSideEffect(ChecklistEffect.Toast(R.string.audit_started))
                    }.onFailure {
                        postSideEffect(ChecklistEffect.Toast(R.string.error_connection))
                    }
                }
                reduce {
                    val newStart = state.startButton.updateEnabled(true)
                    state.copy(startButton = newStart)
                }
                refreshChecklist()
            }
            "ready" -> {
                updateAnswerUseCase(
                    check = state.currentCheckState.check,
                    checkDetails = state.currentCheckState.media.createPatched(),
                ).onSuccess {
                    reduce {
                        val newChecks = state.checks.replaceById(it)
                        state.copy(checks = newChecks)
                    }
                    postSideEffect(ChecklistEffect.Toast(R.string.answer_done))
                }.onFailure {
                    postSideEffect(ChecklistEffect.Toast(R.string.error_happend))
                }
            }
        }
    }

    override fun handleEvent(id: String, event: TextFieldEvent) = blockingIntent {
        when (id) {
            "commentary" -> when (event) {
                is TextFieldEvent.TextChanged -> reduce {
                    val newCommentState =
                        state.currentCheckState.comment.copy(text = event.text)
                    val newCurrentCheckState =
                        state.currentCheckState.copy(comment = newCommentState)
                    state.copy(currentCheckState = newCurrentCheckState)
                }
                is TextFieldEvent.Action -> Unit
            }
        }
    }

    override fun handleEvent(id: String, event: CurrentCheckEvent) = intent {
        when (id) {
            "main" -> when (event) {
                is CurrentCheckEvent.OpenCamera -> openCamera()
                is CurrentCheckEvent.OpenImage -> openImage(event.image)
                is CurrentCheckEvent.Refresh -> refreshCheck()
                is CurrentCheckEvent.RemoveVoice -> removeRecord()
                is CurrentCheckEvent.StartPausePlay -> startPausePlay()
                is CurrentCheckEvent.StartStopRecording -> startStopRecording()
                is CurrentCheckEvent.YesNo -> yesNo(event.yesNo)
            }
        }
    }
}