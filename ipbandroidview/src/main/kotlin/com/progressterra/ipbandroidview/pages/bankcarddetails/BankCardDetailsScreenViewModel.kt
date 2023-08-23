package com.progressterra.ipbandroidview.pages.bankcarddetails

import com.progressterra.ipbandroidview.IpbAndroidViewSettings
import com.progressterra.ipbandroidview.R
import com.progressterra.ipbandroidview.entities.Document
import com.progressterra.ipbandroidview.features.documentphoto.DocumentPhotoEvent
import com.progressterra.ipbandroidview.features.topbar.TopBarEvent
import com.progressterra.ipbandroidview.pages.documentdetails.SaveDocumentsUseCase
import com.progressterra.ipbandroidview.processes.docs.CreateAndSaveDocUseCase
import com.progressterra.ipbandroidview.processes.docs.DocsModule
import com.progressterra.ipbandroidview.processes.docs.DocsModuleUser
import com.progressterra.ipbandroidview.processes.docs.DocumentValidationUseCase
import com.progressterra.ipbandroidview.processes.media.MakePhotoUseCase
import com.progressterra.ipbandroidview.processes.permission.AskPermissionUseCase
import com.progressterra.ipbandroidview.processes.permission.CheckPermissionUseCase
import com.progressterra.ipbandroidview.shared.mvi.BaseViewModel
import com.progressterra.ipbandroidview.shared.ui.button.ButtonEvent
import com.progressterra.ipbandroidview.shared.ui.statebox.ScreenState
import com.progressterra.ipbandroidview.shared.ui.statebox.StateBoxEvent
import com.progressterra.ipbandroidview.shared.ui.textfield.TextFieldEvent

class BankCardDetailsScreenViewModel(
    checkPermissionUseCase: CheckPermissionUseCase,
    makePhotoUseCase: MakePhotoUseCase,
    askPermissionUseCase: AskPermissionUseCase,
    documentValidationUseCase: DocumentValidationUseCase,
    private val fetchCardTemplateUseCase: FetchCardTemplateUseCase,
    private val saveDocumentsUseCase: SaveDocumentsUseCase,
    private val createAndSaveDocUseCase: CreateAndSaveDocUseCase
) : UseBankCardDetailsScreen,
    BaseViewModel<BankCardDetailsScreenState, BankCardDetailsScreenEvent>() {

    override fun createInitialState() = BankCardDetailsScreenState()

    private val docsModule = DocsModule(
        documentValidationUseCase,
        checkPermissionUseCase,
        askPermissionUseCase,
        makePhotoUseCase,
        this,
        object : DocsModuleUser {

            override fun isValid(isValid: Boolean) {
                emitState { it.copy(apply = it.apply.copy(enabled = isValid)) }
            }

            override fun openPhoto(url: String) {
                postEffect(BankCardDetailsScreenEvent.OpenPhoto(url))
            }

            override fun emitModuleState(reducer: (Document) -> Document) {
                emitState { it.copy(document = reducer(currentState.document)) }
            }

            override val moduleState: Document
                get() = currentState.document
        }
    )

    fun setup(newDocument: Document) {
        if (newDocument.isEmpty()) {
            refresh()
        } else {
            emitState { it.copy(screen = it.screen.copy(state = ScreenState.SUCCESS)) }
            docsModule.setup(newDocument)
        }
    }

    fun refresh() {
        onBackground {
            emitState { it.copy(screen = it.screen.copy(state = ScreenState.LOADING)) }
            fetchCardTemplateUseCase().onSuccess { newDocument ->
                emitState {
                    it.copy(screen = it.screen.copy(state = ScreenState.SUCCESS))
                }
                docsModule.setup(newDocument)
            }.onFailure {
                emitState { it.copy(screen = it.screen.copy(state = ScreenState.ERROR)) }
            }
        }
    }

    override fun handle(event: DocumentPhotoEvent) {
        docsModule.handle(event)
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
        docsModule.handle(event)
    }
}