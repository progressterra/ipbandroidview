package com.progressterra.ipbandroidview.pages.wantthis

import android.Manifest
import androidx.lifecycle.ViewModel
import com.progressterra.ipbandroidview.entities.toDocument
import com.progressterra.ipbandroidview.features.documentphoto.DocumentPhotoEvent
import com.progressterra.ipbandroidview.features.profilebutton.ProfileButtonEvent
import com.progressterra.ipbandroidview.features.topbar.TopBarEvent
import com.progressterra.ipbandroidview.pages.documentdetails.SaveDocumentsUseCase
import com.progressterra.ipbandroidview.processes.media.MakePhotoUseCase
import com.progressterra.ipbandroidview.processes.permission.AskPermissionUseCase
import com.progressterra.ipbandroidview.processes.permission.CheckPermissionUseCase
import com.progressterra.ipbandroidview.shared.ScreenState
import com.progressterra.ipbandroidview.shared.ui.button.ButtonEvent
import com.progressterra.ipbandroidview.shared.ui.statebox.StateBoxEvent
import com.progressterra.ipbandroidview.shared.ui.textfield.TextFieldEvent
import com.progressterra.ipbandroidview.shared.ui.textfield.uText
import org.orbitmvi.orbit.ContainerHost
import org.orbitmvi.orbit.annotation.OrbitExperimental
import org.orbitmvi.orbit.syntax.simple.blockingIntent
import org.orbitmvi.orbit.syntax.simple.intent
import org.orbitmvi.orbit.syntax.simple.postSideEffect
import org.orbitmvi.orbit.syntax.simple.reduce
import org.orbitmvi.orbit.viewmodel.container

@OptIn(OrbitExperimental::class)
class WantThisScreenViewModel(
    private val fetchWantThisUseCase: FetchWantThisUseCase,
    private val checkPermissionUseCase: CheckPermissionUseCase,
    private val askPermissionUseCase: AskPermissionUseCase,
    private val makePhotoUseCase: MakePhotoUseCase,
    private val saveDocumentsUseCase: SaveDocumentsUseCase
) : ContainerHost<WantThisScreenState, WantThisScreenEvent>,
    ViewModel(), UseWantThisScreen {

    override val container =
        container<WantThisScreenState, WantThisScreenEvent>(WantThisScreenState())

    fun refresh() {
        intent {
            reduce { state.uScreenState(ScreenState.LOADING) }
            fetchWantThisUseCase().onSuccess {
                reduce { it.uScreenState(ScreenState.SUCCESS) }
            }.onFailure {
                reduce { state.uScreenState(ScreenState.ERROR) }
            }
        }
    }

    override fun handle(event: ProfileButtonEvent) {
        intent {
            when (event.id) {
                "requests" -> postSideEffect(WantThisScreenEvent.Requests)
            }
        }
    }

    override fun handle(event: TopBarEvent) {
        intent {
            postSideEffect(WantThisScreenEvent.Back)
        }
    }

    override fun handle(event: ButtonEvent) {
        intent {
            when (event.id) {
                "send" -> saveDocumentsUseCase(state.toDocument())
                "cancel" -> reduce { state }
            }
        }
    }

    override fun handle(event: DocumentPhotoEvent) {
        intent {
            when (event) {
                is DocumentPhotoEvent.MakePhoto -> checkPermissionUseCase(Manifest.permission.CAMERA).onSuccess {
                    makePhotoUseCase().onSuccess { reduce { state.addPhoto(it) } }
                }.onFailure { askPermissionUseCase(Manifest.permission.CAMERA) }

                is DocumentPhotoEvent.Select -> postSideEffect(WantThisScreenEvent.OpenPhoto(event.image.url))
            }
        }
    }

    override fun handle(event: StateBoxEvent) {
        refresh()
    }

    override fun handle(event: TextFieldEvent) {
        blockingIntent {
            when (event) {
                is TextFieldEvent.Action -> Unit
                is TextFieldEvent.AdditionalAction -> Unit
                is TextFieldEvent.TextChanged -> {
                    reduce {
                        state.updateById(event) {
                            it.uText(event.text)
                        }
                    }
                }
            }
        }
    }
}