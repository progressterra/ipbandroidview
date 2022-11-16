package com.progressterra.ipbandroidview.ui.checklist

import android.Manifest
import android.content.Intent
import android.provider.MediaStore
import androidx.lifecycle.ViewModel
import com.progressterra.ipbandroidview.R
import com.progressterra.ipbandroidview.components.VoiceState
import com.progressterra.ipbandroidview.core.FileExplorer
import com.progressterra.ipbandroidview.core.MakePhotoContract
import com.progressterra.ipbandroidview.core.ManagePermissionContract
import com.progressterra.ipbandroidview.core.ScreenState
import com.progressterra.ipbandroidview.core.voice.AudioManager
import com.progressterra.ipbandroidview.core.voice.VoiceManager
import com.progressterra.ipbandroidview.domain.usecase.checklist.CheckMediaDetailsUseCase
import com.progressterra.ipbandroidview.domain.usecase.checklist.ChecklistUseCase
import com.progressterra.ipbandroidview.domain.usecase.checklist.CreateDocumentUseCase
import com.progressterra.ipbandroidview.domain.usecase.checklist.DocumentChecklistUseCase
import com.progressterra.ipbandroidview.domain.usecase.checklist.FetchExistingAuditUseCase
import com.progressterra.ipbandroidview.domain.usecase.checklist.FinishDocumentUseCase
import com.progressterra.ipbandroidview.domain.usecase.UpdateAnswerUseCase
import com.progressterra.ipbandroidview.ext.createStats
import com.progressterra.ipbandroidview.ext.formPatch
import com.progressterra.ipbandroidview.ext.markLastToRemove
import com.progressterra.ipbandroidview.ext.markToRemove
import com.progressterra.ipbandroidview.ext.replaceById
import com.progressterra.ipbandroidview.model.AuditDocument
import com.progressterra.ipbandroidview.model.CheckPicture
import com.progressterra.ipbandroidview.model.Voice
import kotlinx.coroutines.delay
import org.orbitmvi.orbit.Container
import org.orbitmvi.orbit.ContainerHost
import org.orbitmvi.orbit.syntax.simple.intent
import org.orbitmvi.orbit.syntax.simple.postSideEffect
import org.orbitmvi.orbit.syntax.simple.reduce
import org.orbitmvi.orbit.viewmodel.container

@Suppress("unused", "MemberVisibilityCanBePrivate")
class ChecklistViewModel(
    private val createDocumentUseCase: CreateDocumentUseCase,
    private val finishDocumentUseCase: FinishDocumentUseCase,
    private val updateAnswerUseCase: UpdateAnswerUseCase,
    private val fetchExistingAuditUseCase: FetchExistingAuditUseCase,
    private val documentChecklistUseCase: DocumentChecklistUseCase,
    private val checklistUseCase: ChecklistUseCase,
    private val managePermissionContract: ManagePermissionContract.Client,
    private val voiceManager: VoiceManager,
    private val audioManager: AudioManager,
    private val fileExplorer: FileExplorer,
    private val checkMediaDetailsUseCase: CheckMediaDetailsUseCase,
    private val makePhotoContract: MakePhotoContract.Client
) : ViewModel(), ContainerHost<ChecklistState, ChecklistEffect> {

    override val container: Container<ChecklistState, ChecklistEffect> = container(ChecklistState())

    private val micPermission = Manifest.permission.RECORD_AUDIO

    private val cameraPermission = Manifest.permission.CAMERA

    fun setDocument(auditDocument: AuditDocument) = intent {
        reduce {
            ChecklistState(auditDocument = auditDocument)
        }
        refreshChecklist()
    }

    fun refreshChecklist() = intent {
        reduce { state.copy(checklistScreenState = ScreenState.LOADING) }
        if (state.auditDocument.ongoing) {
            state.auditDocument.documentId?.let {
                documentChecklistUseCase.documentChecklist(it)
                    .onSuccess { checks ->
                        reduce {
                            state.copy(
                                checks = checks,
                                stats = checks.createStats(),
                                checklistScreenState = ScreenState.SUCCESS
                            )
                        }
                    }.onFailure {
                        reduce { state.copy(checklistScreenState = ScreenState.ERROR) }
                    }
            }
        } else {
            checklistUseCase.details(state.auditDocument.checklistId).onSuccess { checks ->
                reduce {
                    state.copy(
                        checks = checks,
                        stats = checks.createStats(),
                        checklistScreenState = ScreenState.SUCCESS
                    )
                }
            }.onFailure {
                reduce { state.copy(checklistScreenState = ScreenState.ERROR) }
            }
        }
    }

    fun openCheck(check: Check) = intent {
        reduce { state.copy(currentCheck = check) }
        refreshCheck()
    }

    fun refreshCheck() = intent {
        state.currentCheck?.let { check ->
            reduce { state.copy(checkScreenState = ScreenState.LOADING) }
            checkMediaDetailsUseCase.checkDetails(check).onSuccess {
                reduce {
                    state.copy(
                        currentCheckMedia = it, checkScreenState = ScreenState.SUCCESS
                    )
                }
                if (it.voices.isNotEmpty()) {
                    reduce { state.copy(voiceState = VoiceState.Player(false, 0f)) }
                }
            }.onFailure {
                reduce { state.copy(checkScreenState = ScreenState.ERROR) }
            }
        }
    }

    fun back() = intent {
        postSideEffect(ChecklistEffect.Back)
    }

    fun yesNo(yes: Boolean) = intent {
        reduce {
            state.copy(
                currentCheck = state.currentCheck?.copy(
                    yesNo = yes
                )
            )
        }
    }

    fun editCheckCommentary(comment: String) = intent {
        reduce {
            state.copy(
                currentCheck = state.currentCheck?.copy(comment = comment)
            )
        }
    }

    fun startStopAudit() = intent {
        if (state.auditDocument.ongoing) {
            state.auditDocument.documentId?.let {
                finishDocumentUseCase.finishDocument(it)
                    .onSuccess {
                        reduce {
                            state.copy(
                                auditDocument = state.auditDocument.copy(ongoing = false)
                            )
                        }
                        postSideEffect(ChecklistEffect.Toast(R.string.audit_ended))
                    }.onFailure {
                        postSideEffect(ChecklistEffect.Toast(R.string.error_connection))
                    }
            }
        } else {
            fetchExistingAuditUseCase.fetchExistingAudit(
                state.auditDocument.placeId, state.auditDocument.checklistId
            ).onSuccess {
                val newDoc = state.auditDocument.copy(documentId = it, ongoing = true)
                reduce {
                    state.copy(auditDocument = newDoc)
                }
                postSideEffect(ChecklistEffect.Toast(R.string.audit_ongoing))
            }.onFailure {
                createDocumentUseCase.createDocument(
                    state.auditDocument.checklistId, state.auditDocument.placeId
                ).onSuccess {
                    val newDoc = state.auditDocument.copy(documentId = it, ongoing = true)
                    reduce { state.copy(auditDocument = newDoc) }
                    postSideEffect(ChecklistEffect.Toast(R.string.audit_started))
                }.onFailure {
                    postSideEffect(ChecklistEffect.Toast(R.string.error_connection))
                }
            }
            refreshChecklist()
        }
    }

    fun startPausePlay() = intent {
        if (state.voiceState.ongoing) {
            audioManager.pause()
            reduce {
                state.copy(
                    voiceState = VoiceState.Player(
                        false, (state.voiceState as VoiceState.Player).progress
                    )
                )
            }
        } else {
            state.currentCheck?.let {
                audioManager.play(
                    state.currentCheckMedia?.voices?.last()?.id!!
                )
            }
            var progress: Float
            do {
                progress = audioManager.progress()
                reduce {
                    state.copy(
                        voiceState = VoiceState.Player(
                            true, progress
                        )
                    )
                }
                if (progress >= 1f) {
                    voiceManager.reset()
                    reduce { state.copy(voiceState = VoiceState.Player(false, 0f)) }
                }
                delay(250)
            } while (state.voiceState.ongoing)
        }
    }

    fun startStopRecording() = intent {
        if (state.voiceState.ongoing) {
            voiceManager.stopRecording()
            reduce {
                state.copy(
                    voiceState = VoiceState.Player(false, 0f)
                )
            }
        } else {
            if (managePermissionContract.checkPermission(micPermission)) {
                val newVoiceId = "TempVoice_${System.currentTimeMillis()}"
                state.currentCheck?.let {
                    voiceManager.startRecording(fileExplorer.audioFile(newVoiceId))
                    reduce {
                        state.copy(
                            voiceState = VoiceState.Recorder(true),
                            currentCheckMedia = state.currentCheckMedia!!.copy(
                                voices = state.currentCheckMedia!!.voices.plus(
                                    Voice(
                                        id = newVoiceId, local = true, toRemove = false
                                    )
                                )
                            )
                        )
                    }
                }
            } else managePermissionContract.requestPermission(micPermission)
        }
    }

    fun remove() = intent {
        audioManager.reset()
        reduce {
            state.copy(
                voiceState = VoiceState.Recorder(false),
                currentCheckMedia = state.currentCheckMedia!!.copy(
                    voices = state.currentCheckMedia!!.voices.markLastToRemove()
                )
            )
        }
    }

    fun applyCheck() = intent {
        updateAnswerUseCase.update(
            check = state.currentCheck!!,
            checkDetails = state.currentCheckMedia!!.copy(
                voices = state.currentCheckMedia!!.voices.formPatch(),
                pictures = state.currentCheckMedia!!.pictures.formPatch()
            ),
        ).onSuccess {
            reduce {
                val newChecks = state.checks.replaceById(it)
                state.copy(
                    checks = newChecks, stats = newChecks.createStats()
                )
            }
            postSideEffect(ChecklistEffect.Toast(R.string.answer_done))
        }.onFailure {
            postSideEffect(ChecklistEffect.Toast(R.string.error_happend))
        }
        audioManager.reset()
        voiceManager.reset()
        fileExplorer.reset()
    }

    fun removePhoto(picture: CheckPicture) = intent {

        reduce {
            val newPictures = state.currentCheckMedia!!.pictures.markToRemove(picture)
            val newCurrentCheckMedia = state.currentCheckMedia!!.copy(pictures = newPictures)
            state.copy(currentCheckMedia = newCurrentCheckMedia)
        }
    }

    fun openImage(picture: CheckPicture) = intent {
        postSideEffect(ChecklistEffect.OpenImage(picture, state.auditDocument.ongoing))
    }

    fun onCamera() = intent {
        if (managePermissionContract.checkPermission(cameraPermission)) {
            val intent = Intent(MediaStore.ACTION_IMAGE_CAPTURE)
            val newPhotoId = "TempPhoto_${System.currentTimeMillis()}"
            val uri = fileExplorer.uriForFile(fileExplorer.pictureFile(newPhotoId))
            intent.putExtra(MediaStore.EXTRA_OUTPUT, uri)
            if (makePhotoContract.makePhoto(intent)) reduce {
                state.copy(
                    currentCheckMedia = state.currentCheckMedia!!.copy(
                        pictures = state.currentCheckMedia!!.pictures.plus(
                            CheckPicture(
                                id = newPhotoId,
                                local = true,
                                toRemove = false,
                                thumbnail = uri.toString(),
                                fullSize = uri.toString()
                            )
                        )
                    )
                )
            }
        } else managePermissionContract.requestPermission(cameraPermission)
    }
}