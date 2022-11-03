package com.progressterra.ipbandroidview.ui.checklist

import android.Manifest
import android.content.Intent
import android.provider.MediaStore
import androidx.lifecycle.ViewModel
import com.progressterra.ipbandroidview.R
import com.progressterra.ipbandroidview.components.VoiceState
import com.progressterra.ipbandroidview.components.stats.ChecklistStats
import com.progressterra.ipbandroidview.components.yesno.YesNo
import com.progressterra.ipbandroidview.core.FileExplorer
import com.progressterra.ipbandroidview.core.ScreenState
import com.progressterra.ipbandroidview.core.permission.ManagePermission
import com.progressterra.ipbandroidview.core.picture.PictureCache
import com.progressterra.ipbandroidview.core.voice.AudioManager
import com.progressterra.ipbandroidview.core.voice.VoiceManager
import com.progressterra.ipbandroidview.domain.CheckMediaDetailsUseCase
import com.progressterra.ipbandroidview.domain.CreateDocumentUseCase
import com.progressterra.ipbandroidview.domain.DocumentChecklistUseCase
import com.progressterra.ipbandroidview.domain.FinishDocumentUseCase
import com.progressterra.ipbandroidview.domain.UpdateAnswerUseCase
import com.progressterra.ipbandroidview.domain.fetchexisting.FetchExistingAuditUseCase
import com.progressterra.ipbandroidview.dto.Checklist
import com.progressterra.ipbandroidview.dto.CheckPicture
import com.progressterra.ipbandroidview.dto.Voice
import com.progressterra.ipbandroidview.ext.formPatch
import com.progressterra.ipbandroidview.ext.markLastToRemove
import com.progressterra.ipbandroidview.ext.markToRemove
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
    private val audioManager: AudioManager,
    private val fileExplorer: FileExplorer,
    private val checkMediaDetailsUseCase: CheckMediaDetailsUseCase,
    private val pictureCache: PictureCache.Client
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
                checks = emptyList(),
                documentId = null
            ),
            stats = ChecklistStats(0, 0, 0, 0),
            voiceState = VoiceState.Recorder(false),
            currentCheck = null,
            screenState = ScreenState.SUCCESS,
            currentCheckMedia = null
        )
    )

    private val micPermission = Manifest.permission.RECORD_AUDIO

    private val cameraPermission = Manifest.permission.CAMERA

    @Suppress("unused")
    fun setDocument(checklist: Checklist) = intent {
        reduce {
            ChecklistState(
                currentCheck = null,
                currentCheckMedia = null,
                checklist = checklist,
                stats = checklist.createStats(),
                voiceState = VoiceState.Recorder(false),
                screenState = ScreenState.SUCCESS,
            )
        }
    }

    override fun openDetails(key: Check) = intent {
        reduce { state.copy(currentCheck = key) }
        refresh()
    }

    override fun closeCheck() = intent {
        audioManager.reset()
        voiceManager.reset()
        fileExplorer.reset()
    }

    override fun refresh() = intent {
        state.currentCheck?.let { check ->
            reduce { state.copy(screenState = ScreenState.LOADING) }
            checkMediaDetailsUseCase.checkDetails(check).onSuccess {
                reduce {
                    state.copy(
                        currentCheckMedia = it,
                        screenState = ScreenState.SUCCESS
                    )
                }
                if (it.voices.isNotEmpty()) {
                    reduce { state.copy(voiceState = VoiceState.Player(false, 0f)) }
                }
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

    override fun editCheckCommentary(comment: String) = intent {
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


    override fun startPausePlay() = intent {
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

    override fun startStopRecording() = intent {
        if (state.voiceState.ongoing) {
            voiceManager.stopRecording()
            reduce {
                state.copy(
                    voiceState = VoiceState.Player(false, 0f)
                )
            }
        } else {
            if (managePermission.checkPermission(micPermission)) {
                val newVoiceId = "TempVoice_${System.currentTimeMillis()}"
                state.currentCheck?.let {
                    voiceManager.startRecording(fileExplorer.audioFile(newVoiceId))
                    reduce {
                        state.copy(
                            voiceState = VoiceState.Recorder(true),
                            currentCheckMedia = state.currentCheckMedia!!.copy(
                                voices = state.currentCheckMedia!!.voices.plus(
                                    Voice(
                                        id = newVoiceId,
                                        local = true,
                                        toRemove = false
                                    )
                                )
                            )
                        )
                    }
                }
            } else
                managePermission.requirePermission(micPermission)
        }
    }

    override fun remove() = intent {
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

    override fun applyCheck() = intent {
        updateAnswerUseCase.update(
            check = state.currentCheck!!,
            checkDetails = state.currentCheckMedia!!.copy(
                voices = state.currentCheckMedia!!.voices.formPatch(),
                pictures = state.currentCheckMedia!!.pictures.formPatch()
            ),
        ).onSuccess {
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
        }.onFailure {
            postSideEffect(ChecklistEffect.Toast(R.string.error_happend))
        }
        postSideEffect(ChecklistEffect.RefreshAudits)
    }

    @Suppress("unused")
    fun removePhoto(picture: CheckPicture) = intent {
        reduce {
            state.copy(
                currentCheckMedia =
                state.currentCheckMedia!!.copy(
                    pictures =
                    state.currentCheckMedia!!.pictures.markToRemove(picture)
                )
            )
        }
    }

    override fun openImage(picture: CheckPicture) = intent {
        postSideEffect(ChecklistEffect.OpenImage(picture, state.checklist.ongoing))
    }

    override fun onCamera() = intent {
        if (managePermission.checkPermission(cameraPermission)) {
            val intent = Intent(MediaStore.ACTION_IMAGE_CAPTURE)
            val newPhotoId = "TempPhoto_${System.currentTimeMillis()}"
            val uri = fileExplorer.uriForFile(fileExplorer.pictureFile(newPhotoId))
            intent.putExtra(MediaStore.EXTRA_OUTPUT, uri)
            pictureCache.intentChannel.send(intent)
            if (pictureCache.thumbnailChannel.receive())
                reduce {
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
        } else
            managePermission.requirePermission(cameraPermission)
    }
}