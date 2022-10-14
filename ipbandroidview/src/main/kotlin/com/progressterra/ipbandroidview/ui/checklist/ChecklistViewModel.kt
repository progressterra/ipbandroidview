package com.progressterra.ipbandroidview.ui.checklist

import android.Manifest
import android.media.MediaPlayer
import android.media.MediaRecorder
import androidx.lifecycle.ViewModel
import com.progressterra.ipbandroidview.composable.VoiceState
import com.progressterra.ipbandroidview.composable.yesno.YesNo
import com.progressterra.ipbandroidview.core.Checklist
import com.progressterra.ipbandroidview.core.ManagePermission
import org.orbitmvi.orbit.Container
import org.orbitmvi.orbit.ContainerHost
import org.orbitmvi.orbit.syntax.simple.intent
import org.orbitmvi.orbit.syntax.simple.postSideEffect
import org.orbitmvi.orbit.syntax.simple.reduce
import org.orbitmvi.orbit.viewmodel.container

class ChecklistViewModel(
    private val managePermission: ManagePermission,
    private val mediaRecorder: MediaRecorder,
    private val mediaPlayer: MediaPlayer
) : ViewModel(), ContainerHost<ChecklistState, ChecklistEffect>,
    ChecklistInteractor {

    override val container: Container<ChecklistState, ChecklistEffect> = container(
        ChecklistState()
    )

    private val permission = Manifest.permission.RECORD_AUDIO

    @Suppress("unused")
    fun setDocument(checklist: Checklist) = intent {
        reduce {
            ChecklistState(
                id = checklist.id,
                name = checklist.name,
                checkCounter = checklist.checks.size,
                repetitiveness = checklist.repetitiveness,
                lastTimeChecked = checklist.lastTimeChecked,
                done = checklist.done,
                ongoing = false,
                checks = checklist.checks
            )
        }
    }

    override fun onCheck(check: Check) = intent {
        reduce { state.copy(currentCheck = check, sheetVisibility = true) }
    }

    override fun back() = intent {
        postSideEffect(ChecklistEffect.OnBack)
    }

    override fun onSheetVisibilityChange(visibility: Boolean) = intent {
        reduce { state.copy(sheetVisibility = visibility) }
        if (!visibility)
            reduce { state.copy(currentCheck = null) }
    }

    override fun closeSheet() = intent {
        reduce { state.copy(sheetVisibility = false, currentCheck = null) }
    }

    override fun yesNo(yes: Boolean) = intent {
        reduce {
            state.copy(
                currentCheck = state.currentCheck?.copy(
                    state = CheckState(
                        state.done,
                        if (yes) YesNo.YES else YesNo.NO
                    )
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

    override fun startStopAudit() {
        TODO("Not yet implemented")
    }

    override fun startPauseVoicePlay() = intent {
        mediaPlayer.setOnBufferingUpdateListener { _, percent ->
            intent {
                reduce {
                    state.copy(
                        voiceState = VoiceState.PLAY(percent / 100f)
                    )
                }
            }
        }
        if (state.voiceState is VoiceState.PLAY) {
            mediaPlayer.pause()
            intent { reduce { state.copy(voiceState = VoiceState.PAUSE(mediaPlayer.currentPosition / mediaPlayer.duration.toFloat())) } }
        }
        if (state.voiceState is VoiceState.PAUSE)
            mediaPlayer.start()
    }

    override fun startStopVoiceRecording() = intent {
        if (managePermission.checkPermission(permission)) {
            if (state.voiceState == VoiceState.IDLE)
                mediaRecorder.start()
            if (state.voiceState == VoiceState.RECORD)
                mediaRecorder.stop()
        } else {
            managePermission.requirePermission(permission)
        }
    }

    override fun removeRecord() {
        TODO("Not yet implemented")
    }

    override fun ready() {
        TODO("Not yet implemented")
    }
}