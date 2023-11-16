package com.progressterra.ipbandroidview.pages.documentdetails

import com.progressterra.ipbandroidview.entities.Document
import com.progressterra.ipbandroidview.features.attachablechat.AttachableChatEvent
import com.progressterra.ipbandroidview.features.attachablechat.AttachableChatModule
import com.progressterra.ipbandroidview.features.attachablechat.AttachableChatState
import com.progressterra.ipbandroidview.features.documentphoto.DocumentPhotoEvent
import com.progressterra.ipbandroidview.features.topbar.TopBarEvent
import com.progressterra.ipbandroidview.processes.chat.FetchDocumentChatUseCase
import com.progressterra.ipbandroidview.processes.chat.FetchMessagesUseCase
import com.progressterra.ipbandroidview.processes.docs.SaveDocumentsUseCase
import com.progressterra.ipbandroidview.processes.chat.SendMessageUseCase
import com.progressterra.ipbandroidview.processes.docs.DocsModule
import com.progressterra.ipbandroidview.processes.docs.DocsModuleUser
import com.progressterra.ipbandroidview.processes.docs.DocumentValidationUseCase
import com.progressterra.ipbandroidview.processes.media.MakePhotoUseCase
import com.progressterra.ipbandroidview.processes.permission.AskPermissionUseCase
import com.progressterra.ipbandroidview.processes.permission.CheckPermissionUseCase
import com.progressterra.ipbandroidview.shared.mvi.AbstractInputViewModel
import com.progressterra.ipbandroidview.shared.mvi.ModuleUser
import com.progressterra.ipbandroidview.shared.ui.button.ButtonEvent
import com.progressterra.ipbandroidview.shared.ui.statecolumn.ScreenState
import com.progressterra.ipbandroidview.shared.ui.statecolumn.StateColumnEvent
import com.progressterra.ipbandroidview.shared.ui.textfield.TextFieldEvent

class DocumentDetailsViewModel(
    fetchMessagesUseCase: FetchMessagesUseCase,
    sendMessageUseCase: SendMessageUseCase,
    checkPermissionUseCase: CheckPermissionUseCase,
    askPermissionUseCase: AskPermissionUseCase,
    makePhotoUseCase: MakePhotoUseCase,
    documentValidationUseCase: DocumentValidationUseCase,
    private val saveDocumentsUseCase: SaveDocumentsUseCase,
    private val fetchDocumentChatUseCase: FetchDocumentChatUseCase
) : AbstractInputViewModel<Document, DocumentDetailsScreenState, DocumentDetailsScreenEffect>(),
    UseDocumentDetailsScreen {

    private val attachableChatModule =
        AttachableChatModule(
            sendMessageUseCase,
            fetchMessagesUseCase,
            this,
            object : ModuleUser<AttachableChatState> {

                override fun emitModuleState(reducer: (AttachableChatState) -> AttachableChatState) {
                    emitState {
                        it.copy(chat = reducer(currentState.chat))
                    }
                }

                override val moduleState: AttachableChatState
                    get() = currentState.chat
            })

    private val docsModule = DocsModule(
        documentValidationUseCase,
        checkPermissionUseCase,
        askPermissionUseCase,
        makePhotoUseCase,
        this,
        object : DocsModuleUser {

            override fun emitModuleState(reducer: (Document) -> Document) {
                emitState { it.copy(document = reducer(moduleState)) }
            }

            override val moduleState: Document
                get() = currentState.document

            override fun isValid(isValid: Boolean) {
                emitState {
                    it.copy(apply = it.apply.copy(enabled = isValid))
                }
            }

            override fun openPhoto(url: String) {
                postEffect(DocumentDetailsScreenEffect.OpenPhoto(url))
            }
        }
    )

    override fun createInitialState() = DocumentDetailsScreenState()

    override fun handle(event: StateColumnEvent) {
        attachableChatModule.handle(event)
        if (event.id == "main") refresh()
    }

    override fun setup(data: Document) {
        emitState { it.copy(document = data) }
        refresh()
    }

    private fun refresh() {
        onBackground {
            emitState { it.copy(screen = it.screen.copy(state = ScreenState.LOADING)) }
            fetchDocumentChatUseCase(
                currentState.document.id,
                currentState.document.name
            ).onSuccess { dialogId ->
                emitState { it.copy(screen = it.screen.copy(state = ScreenState.SUCCESS)) }
                attachableChatModule.setup(dialogId)
                attachableChatModule.refresh()
            }.onFailure {
                emitState { it.copy(screen = it.screen.copy(state = ScreenState.ERROR)) }
            }
        }
    }

    override fun handle(event: AttachableChatEvent) {
        attachableChatModule.handle(event)
    }

    override fun handle(event: TopBarEvent) {
        postEffect(DocumentDetailsScreenEffect.Back)
    }

    override fun handle(event: ButtonEvent) {
        onBackground {
            when (event.id) {
                "apply" -> saveDocumentsUseCase(currentState.document).onSuccess {
                    postEffect(DocumentDetailsScreenEffect.Back)
                }
            }
        }
    }

    override fun handle(event: TextFieldEvent) {
        attachableChatModule.handle(event)
        docsModule.handle(event)
    }

    override fun handle(event: DocumentPhotoEvent) {
        docsModule.handle(event)
    }
}