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
                checks = emptyList()
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
        postSideEffect(ChecklistEffect.OnBack)
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
            }
        else
            fetchExistingAuditUseCase.fetchExistingAudit(
                state.checklist.placeId,
                state.checklist.checklistId
            ).onSuccess {
                reduce { state.copy(checklist = state.checklist.copy(checks = it, ongoing = true)) }
            }.onFailure {
                createDocumentUseCase.createDocument(
                    state.checklist.checklistId,
                    state.checklist.placeId
                ).onSuccess {
                    reduce { state.copy(checklist = state.checklist.copy(ongoing = true)) }
                }.onFailure {
                    postSideEffect(ChecklistEffect.OnToast(R.string.error_connection))
                }
            }
    }

    override fun startPauseVoicePlay() = intent {
//        mediaPlayer.setOnBufferingUpdateListener { _, percent ->
//            intent {
//                reduce {
//                    state.copy(
//                        voiceState = VoiceState.PLAY(percent / 100f)
//                    )
//                }
//            }
//        }
//        if (state.voiceState is VoiceState.PLAY) {
//            mediaPlayer.pause()
//            intent { reduce { state.copy(voiceState = VoiceState.PAUSE(mediaPlayer.currentPosition / mediaPlayer.duration.toFloat())) } }
//        }
//        if (state.voiceState is VoiceState.PAUSE)
//            mediaPlayer.start()
    }

    override fun startStopVoiceRecording() = intent {
//        if (managePermission.checkPermission(permission)) {
//            if (state.voiceState == VoiceState.IDLE)
//                mediaRecorder.start()
//            if (state.voiceState == VoiceState.RECORD)
//                mediaRecorder.stop()
//        } else {
//            managePermission.requirePermission(permission)
//        }
    }

    override fun removeRecord() {
        TODO("Not yet implemented")
    }

    override fun ready() = intent {
        state.currentCheck?.let { updateAnswerUseCase.update(it) }?.onSuccess {
            reduce {
                state.copy(
                    checklist = state.checklist.copy(checks = state.checklist.checks.replaceById(it))
                )
            }
        }
    }
}