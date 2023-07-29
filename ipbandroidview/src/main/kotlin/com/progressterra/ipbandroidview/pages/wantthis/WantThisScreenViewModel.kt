package com.progressterra.ipbandroidview.pages.wantthis

import android.Manifest
import com.progressterra.ipbandroidview.R
import com.progressterra.ipbandroidview.features.documentphoto.DocumentPhotoEvent
import com.progressterra.ipbandroidview.features.profilebutton.ProfileButtonEvent
import com.progressterra.ipbandroidview.features.topbar.TopBarEvent
import com.progressterra.ipbandroidview.processes.media.MakePhotoUseCase
import com.progressterra.ipbandroidview.processes.permission.AskPermissionUseCase
import com.progressterra.ipbandroidview.processes.permission.CheckPermissionUseCase
import com.progressterra.ipbandroidview.shared.BaseViewModel
import com.progressterra.ipbandroidview.shared.ScreenState
import com.progressterra.ipbandroidview.shared.ui.button.ButtonEvent
import com.progressterra.ipbandroidview.shared.ui.statebox.StateBoxEvent
import com.progressterra.ipbandroidview.shared.ui.textfield.TextFieldEvent
import com.progressterra.ipbandroidview.shared.updateById

class WantThisScreenViewModel(
    private val fetchWantThisUseCase: FetchWantThisUseCase,
    private val checkPermissionUseCase: CheckPermissionUseCase,
    private val askPermissionUseCase: AskPermissionUseCase,
    private val makePhotoUseCase: MakePhotoUseCase,
    private val createWantThisRequestUseCase: CreateWantThisRequestUseCase
) : BaseViewModel<WantThisScreenState, WantThisScreenEvent>(), UseWantThisScreen {

    override fun createInitialState() = WantThisScreenState()

    fun refresh() {
        onBackground {
            this.emitState { it.copy(screen = ScreenState.LOADING) }
            fetchWantThisUseCase().onSuccess { newState ->
                this.emitState { newState.copy(screen = ScreenState.SUCCESS) }
            }.onFailure { this.emitState { it.copy(screen = ScreenState.ERROR) } }
        }
    }

    override fun handle(event: ProfileButtonEvent) {
        when (event.id) {
            "requests" -> postEffect(WantThisScreenEvent.Requests)
        }
    }

    override fun handle(event: TopBarEvent) {
        postEffect(WantThisScreenEvent.Back)
    }

    override fun handle(event: ButtonEvent) {
        onBackground {
            when (event.id) {
                "send" -> createWantThisRequestUseCase(currentState.toDocument()).onSuccess {
                    postEffect(WantThisScreenEvent.Toast(R.string.success))
                    postEffect(WantThisScreenEvent.Back)
                }.onFailure {
                    postEffect(WantThisScreenEvent.Toast(R.string.failure))
                }

                "cancel" -> postEffect(WantThisScreenEvent.Back)
            }
        }
    }

    override fun handle(event: DocumentPhotoEvent) {
        onBackground {
            when (event) {
                is DocumentPhotoEvent.MakePhoto -> checkPermissionUseCase(Manifest.permission.CAMERA).onSuccess {
                    makePhotoUseCase().onSuccess { photo ->
                        this.emitState { it.copy(photo = it.photo.copy(items = it.photo.items + photo)) }
                    }
                }.onFailure { askPermissionUseCase(Manifest.permission.CAMERA) }

                is DocumentPhotoEvent.Select -> postEffect(WantThisScreenEvent.OpenPhoto(event.image.url))
            }
        }
    }

    override fun handle(event: StateBoxEvent) {
        refresh()
    }

    override fun handle(event: TextFieldEvent) {
        when (event) {
            is TextFieldEvent.Action -> Unit
            is TextFieldEvent.AdditionalAction -> Unit
            is TextFieldEvent.TextChanged -> emitState {
                it.copy(entries = it.entries.updateById(event) { field ->
                    field.copy(text = event.text)
                })
            }
        }
    }
}