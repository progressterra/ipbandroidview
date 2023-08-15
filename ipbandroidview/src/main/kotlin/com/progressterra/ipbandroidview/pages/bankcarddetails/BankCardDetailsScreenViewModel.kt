package com.progressterra.ipbandroidview.pages.bankcarddetails

import android.Manifest
import com.progressterra.ipbandroidview.features.documentphoto.DocumentPhotoEvent
import com.progressterra.ipbandroidview.features.topbar.TopBarEvent
import com.progressterra.ipbandroidview.pages.documentdetails.SaveDocumentsUseCase
import com.progressterra.ipbandroidview.processes.docs.ValidationUseCase
import com.progressterra.ipbandroidview.processes.media.MakePhotoUseCase
import com.progressterra.ipbandroidview.processes.permission.AskPermissionUseCase
import com.progressterra.ipbandroidview.processes.permission.CheckPermissionUseCase
import com.progressterra.ipbandroidview.shared.BaseViewModel
import com.progressterra.ipbandroidview.shared.ScreenState
import com.progressterra.ipbandroidview.shared.ui.button.ButtonEvent
import com.progressterra.ipbandroidview.shared.ui.textfield.TextFieldEvent

class BankCardDetailsScreenViewModel(
    private val fetchCardTemplateUseCase: FetchCardTemplateUseCase,
    private val saveDocumentsUseCase: SaveDocumentsUseCase,
    private val checkPermissionUseCase: CheckPermissionUseCase,
    private val makePhotoUseCase: MakePhotoUseCase,
    private val askPermissionUseCase: AskPermissionUseCase,
    private val validationUseCase: ValidationUseCase
) : UseBankCardDetailsScreen,
    BaseViewModel<BankCardDetailsScreenState, BankCardDetailsScreenEvent>() {

    override fun createInitialState() = BankCardDetailsScreenState()

    fun setup(newState: BankCardDetailsScreenState) {
        if (newState.isEmpty()) {
            refresh()
        } else {
            emitState { newState.copy(screen = ScreenState.SUCCESS) }
        }
    }

    fun refresh() {
        onBackground {
            emitState { it.copy(screen = ScreenState.LOADING) }
            fetchCardTemplateUseCase().onSuccess { newState ->
                emitState { newState.copy(screen = ScreenState.SUCCESS) }
            }.onFailure { emitState { it.copy(screen = ScreenState.ERROR) } }
        }
    }

    override fun handle(event: DocumentPhotoEvent) {
        onBackground {
            when (event) {
                is DocumentPhotoEvent.MakePhoto -> checkPermissionUseCase(Manifest.permission.CAMERA).onSuccess {
                    makePhotoUseCase().onSuccess { photo ->
                        emitState {
                            it.copy(photo = it.photo.copy(items = it.photo.items + photo))
                        }
                    }
                    validation()
                }.onFailure { askPermissionUseCase(Manifest.permission.CAMERA) }

                is DocumentPhotoEvent.Select -> postEffect(
                    BankCardDetailsScreenEvent.OpenPhoto(
                        event.image.url
                    )
                )
            }
        }
    }

    private fun validation() {
        onBackground {
            val valid = validationUseCase(currentState.toDocument())
            emitState {
                it.copy(apply = it.apply.copy(enabled = valid.isSuccess))
            }
        }
    }

    override fun handle(event: TopBarEvent) {
        postEffect(BankCardDetailsScreenEvent.Back)
    }

    override fun handle(event: ButtonEvent) {
        onBackground {
            when (event.id) {
                "apply" -> saveDocumentsUseCase(currentState.toDocument()).onSuccess {
                    postEffect(BankCardDetailsScreenEvent.Back)
                }
            }
        }
    }

    override fun handle(event: TextFieldEvent) {

    }
}