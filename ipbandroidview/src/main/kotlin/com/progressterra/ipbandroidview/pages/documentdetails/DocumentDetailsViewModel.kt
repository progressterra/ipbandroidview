package com.progressterra.ipbandroidview.pages.documentdetails

import android.Manifest
import com.progressterra.ipbandroidview.features.documentphoto.DocumentPhotoEvent
import com.progressterra.ipbandroidview.features.topbar.TopBarEvent
import com.progressterra.ipbandroidview.processes.media.MakePhotoUseCase
import com.progressterra.ipbandroidview.processes.permission.AskPermissionUseCase
import com.progressterra.ipbandroidview.processes.permission.CheckPermissionUseCase
import com.progressterra.ipbandroidview.shared.BaseViewModel
import com.progressterra.ipbandroidview.shared.ui.button.ButtonEvent
import com.progressterra.ipbandroidview.shared.ui.textfield.TextFieldEvent
import com.progressterra.ipbandroidview.shared.updateById

class DocumentDetailsViewModel(
    private val saveDocumentsUseCase: SaveDocumentsUseCase,
    private val checkPermissionUseCase: CheckPermissionUseCase,
    private val askPermissionUseCase: AskPermissionUseCase,
    private val makePhotoUseCase: MakePhotoUseCase,
    private val validationUseCase: ValidationUseCase
) : BaseViewModel<DocumentDetailsState, DocumentDetailsEvent>(), UseDocumentDetails {

    override val initialState = DocumentDetailsState()

    fun setup(state: DocumentDetailsState) {
        fastEmitState { state }
    }

    override fun handle(event: TopBarEvent) {
        postEffect(DocumentDetailsEvent.Back)
    }

    override fun handle(event: ButtonEvent) {
        onBackground {
            when (event.id) {
                "apply" -> saveDocumentsUseCase(state.value.toDocument()).onSuccess {
                    postEffect(DocumentDetailsEvent.Back)
                }
            }
        }
    }

    override fun handle(event: TextFieldEvent) {
        when (event) {
            is TextFieldEvent.Action -> Unit
            is TextFieldEvent.AdditionalAction -> Unit
            is TextFieldEvent.TextChanged -> {
                fastEmitState {
                    it.copy(
                        entries = it.entries.updateById(event) { field ->
                            field.copy(text = event.text)
                        }
                    )
                }
                validation()
            }
        }
    }

    private fun validation() {
        onBackground {
            val valid = validationUseCase(state.value)
            emitState {
                it.copy(apply = it.apply.copy(enabled = valid.isSuccess))
            }
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

                is DocumentPhotoEvent.Select -> postEffect(DocumentDetailsEvent.OpenPhoto(event.image.url))
            }
        }
    }
}