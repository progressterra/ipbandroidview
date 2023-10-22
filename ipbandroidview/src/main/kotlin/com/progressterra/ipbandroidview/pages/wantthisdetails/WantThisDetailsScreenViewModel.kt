package com.progressterra.ipbandroidview.pages.wantthisdetails

import com.progressterra.ipbandroidview.entities.Document
import com.progressterra.ipbandroidview.entities.toScreenState
import com.progressterra.ipbandroidview.features.attachablechat.AttachableChatEvent
import com.progressterra.ipbandroidview.features.attachablechat.AttachableChatModule
import com.progressterra.ipbandroidview.features.attachablechat.AttachableChatState
import com.progressterra.ipbandroidview.features.documentphoto.DocumentPhotoEvent
import com.progressterra.ipbandroidview.features.storecard.StoreCardEvent
import com.progressterra.ipbandroidview.features.topbar.TopBarEvent
import com.progressterra.ipbandroidview.processes.SaveDocumentsUseCase
import com.progressterra.ipbandroidview.processes.FetchMessagesUseCase
import com.progressterra.ipbandroidview.processes.FetchWantThisDetailsChatUseCase
import com.progressterra.ipbandroidview.processes.SendMessageUseCase
import com.progressterra.ipbandroidview.processes.cart.AddToCartUseCase
import com.progressterra.ipbandroidview.processes.cart.RemoveFromCartUseCase
import com.progressterra.ipbandroidview.processes.docs.DocsModule
import com.progressterra.ipbandroidview.processes.docs.DocsModuleUser
import com.progressterra.ipbandroidview.processes.docs.DocumentValidationUseCase
import com.progressterra.ipbandroidview.processes.goods.FetchSingleGoodsUseCase
import com.progressterra.ipbandroidview.processes.media.MakePhotoUseCase
import com.progressterra.ipbandroidview.processes.permission.AskPermissionUseCase
import com.progressterra.ipbandroidview.processes.permission.CheckPermissionUseCase
import com.progressterra.ipbandroidview.shared.mvi.AbstractInputViewModel
import com.progressterra.ipbandroidview.shared.mvi.ModuleUser
import com.progressterra.ipbandroidview.shared.ui.button.ButtonEvent
import com.progressterra.ipbandroidview.shared.ui.counter.CounterEvent
import com.progressterra.ipbandroidview.shared.ui.statecolumn.ScreenState
import com.progressterra.ipbandroidview.shared.ui.statecolumn.StateColumnEvent
import com.progressterra.ipbandroidview.shared.ui.textfield.TextFieldEvent

class WantThisDetailsScreenViewModel(
    fetchMessagesUseCase: FetchMessagesUseCase,
    sendMessageUseCase: SendMessageUseCase,
    checkPermissionUseCase: CheckPermissionUseCase,
    askPermissionUseCase: AskPermissionUseCase,
    makePhotoUseCase: MakePhotoUseCase,
    documentValidationUseCase: DocumentValidationUseCase,
    private val fetchWantThisDetailsChatUseCase: FetchWantThisDetailsChatUseCase,
    private val addToCartUseCase: AddToCartUseCase,
    private val removeFromCartUseCase: RemoveFromCartUseCase,
    private val fetchSingleGoodsUseCase: FetchSingleGoodsUseCase,
    private val saveDocumentsUseCase: SaveDocumentsUseCase
) : AbstractInputViewModel<Document, WantThisDetailsScreenState, WantThisDetailsScreenEffect>(),
    UseWantThisDetailsScreen {

    override fun createInitialState() = WantThisDetailsScreenState()

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
                emitState { it.copy(document = reducer(currentState.document)) }
            }

            override val moduleState: Document
                get() = currentState.document

            override fun isValid(isValid: Boolean) {
                emitState {
                    it.copy(apply = it.apply.copy(enabled = isValid))
                }
            }

            override fun openPhoto(url: String) {
                postEffect(WantThisDetailsScreenEffect.OpenPhoto(url))
            }
        }
    )

    override fun setup(data: Document) {
        emitState { it.copy(document = data) }
        refresh()
    }

    private fun refresh() {
        onBackground {
            var isSuccess = true
            emitState { it.copy(screen = it.screen.copy(state = ScreenState.LOADING)) }
            fetchWantThisDetailsChatUseCase(
                currentState.document.id,
                currentState.document.name
            ).onSuccess { dialogId ->
                attachableChatModule.setup(dialogId)
                attachableChatModule.refresh()
            }.onFailure {
                isSuccess = false
            }
            fetchSingleGoodsUseCase(
                currentState.document.additionalValue
            ).onSuccess { newStoreCard ->
                emitState { it.copy(storeCard = newStoreCard) }
            }.onFailure {
                isSuccess = false
            }
            emitState { it.copy(screen = it.screen.copy(state = isSuccess.toScreenState())) }
        }
    }

    override fun handle(event: StoreCardEvent) {
        onBackground {
            when (event) {
                is StoreCardEvent.AddToCart -> addToCartUseCase(event.id).onSuccess {
                    refresh()
                }

                is StoreCardEvent.Open -> postEffect(
                    WantThisDetailsScreenEffect.GoodsDetails(
                        event.id
                    )
                )
            }
        }
    }

    override fun handle(event: DocumentPhotoEvent) {
        docsModule.handle(event)
    }

    override fun handle(event: CounterEvent) {
        onBackground {
            when (event) {
                is CounterEvent.Add -> addToCartUseCase(event.id).onSuccess {
                    refresh()
                }

                is CounterEvent.Remove -> removeFromCartUseCase(event.id).onSuccess {
                    refresh()
                }
            }
        }
    }

    override fun handle(event: WantThisDetailsScreenEvent) {
        attachableChatModule.open()
    }

    override fun handle(event: ButtonEvent) {
        onBackground {
            when (event.id) {
                "apply" -> saveDocumentsUseCase(currentState.document).onSuccess {
                    postEffect(WantThisDetailsScreenEffect.Back)
                }
            }
        }
    }

    override fun handle(event: AttachableChatEvent) {
        attachableChatModule.handle(event)
    }

    override fun handle(event: TopBarEvent) {
        postEffect(WantThisDetailsScreenEffect.Back)
    }

    override fun handle(event: StateColumnEvent) {
        if (event.id == "main") refresh()
    }

    override fun handle(event: TextFieldEvent) {
        attachableChatModule.handle(event)
        docsModule.handle(event)
    }
}