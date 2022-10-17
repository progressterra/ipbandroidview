package com.progressterra.ipbandroidview.ui.checklist

import android.Manifest
import android.util.Log
import androidx.lifecycle.ViewModel
import com.progressterra.ipbandroidview.R
import com.progressterra.ipbandroidview.composable.VoiceState
import com.progressterra.ipbandroidview.composable.yesno.YesNo
import com.progressterra.ipbandroidview.core.Checklist
import com.progressterra.ipbandroidview.core.ManagePermission
import com.progressterra.ipbandroidview.core.voice.AudioManager
import com.progressterra.ipbandroidview.core.voice.AudioProgressListener
import com.progressterra.ipbandroidview.core.voice.VoiceManager
import com.progressterra.ipbandroidview.domain.CreateDocumentUseCase
import com.progressterra.ipbandroidview.domain.DocumentChecklistUseCase
import com.progressterra.ipbandroidview.domain.FinishDocumentUseCase
import com.progressterra.ipbandroidview.domain.UpdateAnswerUseCase
import com.progressterra.ipbandroidview.domain.fetchexisting.FetchExistingAuditUseCase
import com.progressterra.ipbandroidview.ext.replaceById
import kotlinx.coroutines.delay
import org.orbitmvi.orbit.Container
import org.orbitmvi.orbit.ContainerHost
import org.orbitmvi.orbit.syntax.simple.intent
import org.orbitmvi.orbit.syntax.simple.postSideEffect
import org.orbitmvi.orbit.syntax.simple.reduce
import org.orbitmvi.orbit.viewmodel.container

class ChecklistViewModel(
    private val createDocumentUseCase: CreateDocumentUseCase,
    private val finishDocumentUseCase: FinishDocumentUseCase,
    private val updateAnswerUseCase: UpdateAnswerUseCase,
    private val fetchExistingAuditUseCase: FetchExistingAuditUseCase,
    private val documentChecklistUseCase: DocumentChecklistUseCase,
    private val managePermission: ManagePermission,
    private val voiceManager: VoiceManager,
    private val audioManager: AudioManager
) : ViewModel(), ContainerHost<ChecklistState, ChecklistEffect>,
    ChecklistInteractor, AudioProgressListener {

    override val container: Container<ChecklistState, ChecklistEffect> = container(
        ChecklistState(
            checklist = Checklist(
                checklistId = "",
                placeId = "",
                name = "",
                done = false,
                ongoing = false,
                repetitiveness = "",
                lastTimeChecked = "",
                checks = emptyList(),
                documentId = null
            )
        )
    )

    init {
        audioManager.setListener(this)
    }

    private val permission = Manifest.permission.RECORD_AUDIO

    @Suppress("unused")
    fun setDocument(checklist: Checklist) = intent {
        reduce {
            ChecklistState(
                currentCheck = null,
                checklist = checklist
            )
        }
    }

    override fun onCheck(check: Check?) = intent {
        if (state.currentCheck != null && check == null) {
            audioManager.reset()
            voiceManager.reset()
        }
        reduce { state.copy(currentCheck = check) }
    }

    override fun back() = intent {
        postSideEffect(ChecklistEffect.Back)
    }

    override fun yesNo(yes: Boolean) = intent {
        reduce {
            state.copy(
                currentCheck = state.currentCheck?.copy(
                    yesNo = if (yes) YesNo.YES else YesNo.NO
                )
            )
        }
    }

    override fun onCheckCommentaryChange(comment: String) = intent {
        reduce {
            state.copy(
                currentCheck = state.currentCheck?.copy(comment = comment)
            )
        }
    }

    override fun startStopAudit() = intent {
        if (state.checklist.ongoing)
            state.checklist.documentId?.let { documentId ->
                finishDocumentUseCase.finishDocument(documentId).onSuccess {
                    reduce { state.copy(checklist = state.checklist.copy(ongoing = false)) }
                    postSideEffect(ChecklistEffect.Toast(R.string.audit_ended))
                }.onFailure {
                    postSideEffect(ChecklistEffect.Toast(R.string.error_connection))
                }
            }
        else {
            fetchExistingAuditUseCase.fetchExistingAudit(
                state.checklist.placeId,
                state.checklist.checklistId
            ).onSuccess {
                reduce {
                    state.copy(
                        checklist = state.checklist.copy(
                            documentId = it
                        )
                    )
                }
            }.onFailure {
                createDocumentUseCase.createDocument(
                    state.checklist.checklistId,
                    state.checklist.placeId
                ).onSuccess {
                    reduce {
                        state.copy(
                            checklist = state.checklist.copy(
                                documentId = it
                            )
                        )
                    }
                }.onFailure {
                    postSideEffect(ChecklistEffect.Toast(R.string.error_connection))
                }
            }
            state.checklist.documentId?.let { id ->
                documentChecklistUseCase.documentChecklist(id).onSuccess { checks ->
                    reduce {
                        state.copy(
                            checklist = state.checklist.copy(
                                checks = checks,
                                ongoing = true
                            )
                        )
                    }
                    postSideEffect(ChecklistEffect.Toast(R.string.audit_started))
                }.onFailure {
                    postSideEffect(ChecklistEffect.Toast(R.string.error_connection))
                }
            }
        }
        postSideEffect(ChecklistEffect.RefreshAudits)
    }


    override fun startPlay() {
        intent {
            state.currentCheck?.let {
                audioManager.startPlay(
                    it.id,
                    (state.voiceState as VoiceState.Player).progress
                )
                reduce {
                    state.copy(
                        voiceState = VoiceState.Player(
                            true,
                            (state.voiceState as VoiceState.Player).progress
                        )
                    )
                }
            }
        }
    }

    override fun progress(progress: Float) = intent {
        reduce { state.copy(voiceState = VoiceState.Player(state.voiceState.ongoing, progress)) }
    }

    override fun ended() = intent {
        voiceManager.stopRecording()
        reduce { state.copy(voiceState = VoiceState.Player(false, 0f)) }
    }

    override fun pausePlay() = intent {
        audioManager.stopPlay()
        reduce {
            state.copy(
                voiceState = VoiceState.Player(
                    false, (state.voiceState as VoiceState.Player).progress
                )
            )
        }
    }

    override fun startRecording() = intent {
        if (managePermission.checkPermission(permission)) {
            state.currentCheck?.let {
                voiceManager.startRecording(it.id)
                reduce { state.copy(voiceState = VoiceState.Recorder(true)) }
            }
        } else {
            managePermission.requirePermission(permission)
        }
    }

    override fun stopRecording() = intent {
        voiceManager.stopRecording()
        reduce { state.copy(voiceState = VoiceState.Player(false, 0f)) }
    }

    override fun removeRecord() = intent {
        reduce { state.copy(voiceState = VoiceState.Recorder(false)) }
    }

    override fun ready() = intent {
        state.currentCheck?.let { updateAnswerUseCase.update(it) }?.onSuccess {
            reduce {
                state.copy(
                    checklist = state.checklist.copy(
                        checks = state.checklist.checks.replaceById(
                            it
                        )
                    )
                )
            }
            postSideEffect(ChecklistEffect.Toast(R.string.answer_done))
        }?.onFailure {
            postSideEffect(ChecklistEffect.Toast(R.string.error_happend))
        }
    }
}