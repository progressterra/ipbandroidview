package com.progressterra.ipbandroidview.ui.checklist

import android.Manifest
import android.media.MediaPlayer
import android.media.MediaRecorder
import androidx.lifecycle.ViewModel
import com.progressterra.ipbandroidview.R
import com.progressterra.ipbandroidview.composable.yesno.YesNo
import com.progressterra.ipbandroidview.core.Checklist
import com.progressterra.ipbandroidview.core.ManagePermission
import com.progressterra.ipbandroidview.domain.CreateDocumentUseCase
import com.progressterra.ipbandroidview.domain.DocumentChecklistUseCase
import com.progressterra.ipbandroidview.domain.FinishDocumentUseCase
import com.progressterra.ipbandroidview.domain.UpdateAnswerUseCase
import com.progressterra.ipbandroidview.domain.fetchexisting.FetchExistingAuditUseCase
import com.progressterra.ipbandroidview.ext.replaceById
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
    private val mediaRecorder: MediaRecorder,
    private val mediaPlayer: MediaPlayer
) : ViewModel(), ContainerHost<ChecklistState, ChecklistEffect>,
    ChecklistInteractor {

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

    override fun onCheck(check: Check) = intent {
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
            finishDocumentUseCase.finishDocument(state.checklist.checklistId).onSuccess {
                reduce { state.copy(checklist = state.checklist.copy(ongoing = false)) }
                postSideEffect(ChecklistEffect.Toast(R.string.audit_ended))
            }.onFailure {
                postSideEffect(ChecklistEffect.Toast(R.string.error_connection))
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

    override fun startPauseVoicePlay() = intent {

    }

    override fun startStopVoiceRecording() = intent {

    }

    override fun removeRecord() {

    }

    override fun ready() = intent {
        state.currentCheck?.let { updateAnswerUseCase.update(it) }?.onSuccess {
            reduce {
                state.copy(
                    checklist = state.checklist.copy(checks = state.checklist.checks.replaceById(it))
                )
            }
            postSideEffect(ChecklistEffect.Toast(R.string.answer_done))
        }?.onFailure {
            postSideEffect(ChecklistEffect.Toast(R.string.error_happend))
        }
    }
}