package com.progressterra.ipbandroidview.pages.bankcarddetails

import android.Manifest
import com.progressterra.ipbandroidview.IpbAndroidViewSettings
import com.progressterra.ipbandroidview.R
import com.progressterra.ipbandroidview.entities.Document
import com.progressterra.ipbandroidview.features.documentphoto.DocumentPhotoEvent
import com.progressterra.ipbandroidview.features.topbar.TopBarEvent
import com.progressterra.ipbandroidview.pages.documentdetails.SaveDocumentsUseCase
import com.progressterra.ipbandroidview.processes.docs.CreateAndSaveDocUseCase
import com.progressterra.ipbandroidview.processes.docs.DocumentValidationUseCase
import com.progressterra.ipbandroidview.processes.media.MakePhotoUseCase
import com.progressterra.ipbandroidview.processes.permission.AskPermissionUseCase
import com.progressterra.ipbandroidview.processes.permission.CheckPermissionUseCase
import com.progressterra.ipbandroidview.shared.BaseViewModel
import com.progressterra.ipbandroidview.shared.ScreenState
import com.progressterra.ipbandroidview.shared.ui.button.ButtonEvent
import com.progressterra.ipbandroidview.shared.ui.button.ButtonState
import com.progressterra.ipbandroidview.shared.ui.statebox.StateBoxEvent
import com.progressterra.ipbandroidview.shared.ui.textfield.TextFieldEvent
import com.progressterra.ipbandroidview.shared.updateById

class BankCardDetailsScreenViewModel(
    private val fetchCardTemplateUseCase: FetchCardTemplateUseCase,
    private val saveDocumentsUseCase: SaveDocumentsUseCase,
    private val checkPermissionUseCase: CheckPermissionUseCase,
    private val makePhotoUseCase: MakePhotoUseCase,
    private val askPermissionUseCase: AskPermissionUseCase,
    private val documentValidationUseCase: DocumentValidationUseCase,
    private val createAndSaveDocUseCase: CreateAndSaveDocUseCase
) : UseBankCardDetailsScreen,
    BaseViewModel<BankCardDetailsScreenState, BankCardDetailsScreenEvent>() {

    override fun createInitialState() = BankCardDetailsScreenState(
        apply = ButtonState(id = "apply"),
        screen = ScreenState.LOADING,
        document = Document()
    )

    fun setup(newDocument: Document) {
        if (newDocument.isEmpty()) {
            refresh()
        } else {
            emitState { it.copy(document = newDocument, screen = ScreenState.SUCCESS) }
            validation()
        }
    }

    fun refresh() {
        onBackground {
            emitState { it.copy(screen = ScreenState.LOADING) }
            fetchCardTemplateUseCase().onSuccess { newDocument ->
                emitState { it.copy(document = newDocument, screen = ScreenState.SUCCESS) }
                validation()
            }.onFailure { emitState { it.copy(screen = ScreenState.ERROR) } }
        }
    }

    override fun handle(event: DocumentPhotoEvent) {
        onBackground {
            when (event) {
                is DocumentPhotoEvent.MakePhoto -> checkPermissionUseCase(Manifest.permission.CAMERA).onSuccess {
                    makePhotoUseCase().onSuccess { photo ->
                        emitState {
                            it.copy(document = it.document.copy(photo = it.document.photo.copy(items = it.document.photo.items + photo)))
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
            val valid = documentValidationUseCase(currentState.document)
            emitState {
                it.copy(apply = it.apply.copy(enabled = valid.isSuccess))
            }
        }
    }

    override fun handle(event: TopBarEvent) {
        postEffect(BankCardDetailsScreenEvent.Back)
    }

    override fun handle(event: StateBoxEvent) {
        refresh()
    }

    override fun handle(event: ButtonEvent) {
        onBackground {
            when (event.id) {
                "apply" -> if (currentState.document.isTemplate()) {
                    createAndSaveDocUseCase(
                        currentState.document,
                        IpbAndroidViewSettings.BANK_CARDS_TYPE_ID
                    ).onSuccess {
                        postEffect(BankCardDetailsScreenEvent.Toast(R.string.success))
                        postEffect(BankCardDetailsScreenEvent.Back)
                    }.onFailure {
                        postEffect(BankCardDetailsScreenEvent.Toast(R.string.failure))
                    }
                } else {
                    saveDocumentsUseCase(currentState.document).onSuccess {
                        postEffect(BankCardDetailsScreenEvent.Back)
                        postEffect(BankCardDetailsScreenEvent.Toast(R.string.success))
                    }.onFailure {
                        postEffect(BankCardDetailsScreenEvent.Toast(R.string.failure))
                    }
                }
            }
        }
    }

    override fun handle(event: TextFieldEvent) {
        when (event) {
            is TextFieldEvent.Action -> Unit
            is TextFieldEvent.AdditionalAction -> Unit
            is TextFieldEvent.TextChanged -> {
                emitState {
                    it.copy(
                        document = it.document.copy(
                            entries = it.document.entries.updateById(event) { field ->
                                field.copy(text = event.text)
                            }
                        )
                    )
                }
                validation()
            }
        }
    }
}