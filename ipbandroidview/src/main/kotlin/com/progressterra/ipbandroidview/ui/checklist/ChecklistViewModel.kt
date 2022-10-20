package com.progressterra.ipbandroidview.ui.checklist

import android.Manifest
import android.content.Intent
import android.provider.MediaStore
import android.util.Log
import androidx.lifecycle.ViewModel
import com.progressterra.ipbandroidview.R
import com.progressterra.ipbandroidview.composable.VoiceState
import com.progressterra.ipbandroidview.composable.stats.ChecklistStats
import com.progressterra.ipbandroidview.composable.yesno.YesNo
import com.progressterra.ipbandroidview.core.Checklist
import com.progressterra.ipbandroidview.core.FileExplorer
import com.progressterra.ipbandroidview.core.ScreenState
import com.progressterra.ipbandroidview.core.StartActivityCache
import com.progressterra.ipbandroidview.core.permission.ManagePermission
import com.progressterra.ipbandroidview.core.voice.AudioManager
import com.progressterra.ipbandroidview.core.voice.VoiceManager
import com.progressterra.ipbandroidview.domain.CheckMediaDetailsUseCase
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
import java.io.File

class ChecklistViewModel(
    private val createDocumentUseCase: CreateDocumentUseCase,
    private val finishDocumentUseCase: FinishDocumentUseCase,
    private val updateAnswerUseCase: UpdateAnswerUseCase,
    private val fetchExistingAuditUseCase: FetchExistingAuditUseCase,
    private val documentChecklistUseCase: DocumentChecklistUseCase,
    private val managePermission: ManagePermission,
    private val voiceManager: VoiceManager,
    private val audioManager: AudioManager,
    private val fileExplorer: FileExplorer,
    private val startActivityCache: StartActivityCache,
    private val checkMediaDetailsUseCase: CheckMediaDetailsUseCase
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
            ),
            stats = ChecklistStats(0, 0, 0, 0),
            photos = emptyList(),
            voiceState = VoiceState.Recorder(false),
            currentCheck = null,
            screenState = ScreenState.SUCCESS,
            currentCheckDetails = null
        )
    )

    private val micPermission = Manifest.permission.RECORD_AUDIO

    private val cameraPermission = Manifest.permission.CAMERA

    @Suppress("unused")
    fun setDocument(checklist: Checklist) = intent {
        reduce {
            ChecklistState(
                currentCheck = null,
                currentCheckDetails = null,
                checklist = checklist,
                stats = checklist.createStats(),
                photos = emptyList(),
                voiceState = VoiceState.Recorder(false),
                screenState = ScreenState.SUCCESS
            )
        }
    }

    override fun onCheck(check: Check) = intent {
        if (state.currentCheck != null) {
            Log.d("RESET", "onCheck reset")
            try {
                audioManager.reset()
                voiceManager.reset()
            } catch (e: Exception) {
                Log.e("RESET", e.message, e)
            }
            reduce { state.copy(photos = emptyList(), voiceState = VoiceState.Recorder(false)) }
        }
        reduce { state.copy(currentCheck = check) }
        refresh()
    }

    override fun refresh() = intent {
        state.currentCheck?.let {
            reduce { state.copy(screenState = ScreenState.LOADING) }
            checkMediaDetailsUseCase.checkDetails(it).onSuccess {
                reduce { state.copy(currentCheckDetails = it, screenState = ScreenState.SUCCESS) }
            }.onFailure {
                reduce { state.copy(screenState = ScreenState.ERROR) }
            }
        }
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
                audioManager.play(
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
        intent {
            var progress: Float
            while (state.voiceState.ongoing) {
                Log.d("AUDIO", "state: ${state.voiceState.ongoing}")
                progress = audioManager.progress()
                Log.d("AUDIO", "progress: $progress")
                reduce {
                    state.copy(
                        voiceState = VoiceState.Player(
                            state.voiceState.ongoing,
                            progress
                        )
                    )
                }
                if (progress >= 1f) {
                    voiceManager.reset()
                    reduce { state.copy(voiceState = VoiceState.Player(false, 0f)) }
                }
                delay(250)
            }
        }
    }

    override fun pausePlay() = intent {
        audioManager.pause()
        reduce {
            state.copy(
                voiceState = VoiceState.Player(
                    false, (state.voiceState as VoiceState.Player).progress
                )
            )
        }
    }

    override fun startRecording() = intent {
        if (managePermission.checkPermission(micPermission)) {
            state.currentCheck?.let {
                voiceManager.startRecording(it.id)
                reduce { state.copy(voiceState = VoiceState.Recorder(true)) }
            }
        } else
            managePermission.requirePermission(micPermission)
    }

    override fun stopRecording() = intent {
        voiceManager.stopRecording()
        reduce { state.copy(voiceState = VoiceState.Player(false, 0f)) }
    }

    override fun removeRecord() = intent {
        audioManager.reset()
        reduce { state.copy(voiceState = VoiceState.Recorder(false)) }
    }

    override fun ready() = intent {
        state.currentCheck?.let {
            updateAnswerUseCase.update(
                check = it,
                state.photos,
                fileExplorer.exist(it.id)
            )
        }?.onSuccess {
            val newChecklist = state.checklist.copy(
                checks = state.checklist.checks.replaceById(it)
            )
            reduce {
                state.copy(
                    checklist = newChecklist,
                    stats = newChecklist.createStats()
                )
            }
            postSideEffect(ChecklistEffect.Toast(R.string.answer_done))
        }?.onFailure {
            postSideEffect(ChecklistEffect.Toast(R.string.error_happend))
        }
    }

    @Suppress("unused")
    fun removePhoto(id: String) = intent {
        fileExplorer.deletePicture(id)
        val newPhotos = state.photos.toMutableList()
        newPhotos.remove(id)
        reduce { state.copy(photos = newPhotos) }
    }

    override fun openImage(id: String) = intent {
        postSideEffect(ChecklistEffect.Image(id, !state.checklist.ongoing))
    }

    override fun onCamera() = intent {
        if (managePermission.checkPermission(cameraPermission)) {
            Log.d("Camera started", "on camera intent sended")
            startActivityCache.startActivityFromIntent(Intent(MediaStore.ACTION_IMAGE_CAPTURE).apply {
                val newPhotoId = state.photos.size.toString()
                val photoFile: File = File.createTempFile(
                    newPhotoId,
                    ".jpg",
                    fileExplorer.picturesFolder()
                ).apply {
                    val newPhotos = state.photos.toMutableList()
                    newPhotos.add(newPhotoId)
                    reduce { state.copy(photos = newPhotos) }
                }
                putExtra(MediaStore.EXTRA_OUTPUT, fileExplorer.uriForFile(photoFile))
            })
        } else
            managePermission.requirePermission(cameraPermission)
    }
}