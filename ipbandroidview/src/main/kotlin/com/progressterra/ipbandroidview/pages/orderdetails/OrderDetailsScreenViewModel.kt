package com.progressterra.ipbandroidview.pages.orderdetails

import com.progressterra.ipbandroidview.entities.toScreenState
import com.progressterra.ipbandroidview.features.attachablechat.AttachableChatEvent
import com.progressterra.ipbandroidview.features.attachablechat.AttachableChatModule
import com.progressterra.ipbandroidview.features.attachablechat.AttachableChatState
import com.progressterra.ipbandroidview.features.ordercard.OrderCardEvent
import com.progressterra.ipbandroidview.features.orderdetails.OrderDetailsEvent
import com.progressterra.ipbandroidview.features.topbar.TopBarEvent
import com.progressterra.ipbandroidview.pages.support.FetchMessagesUseCase
import com.progressterra.ipbandroidview.pages.support.SendMessageUseCase
import com.progressterra.ipbandroidview.shared.mvi.BaseViewModel
import com.progressterra.ipbandroidview.shared.mvi.ModuleUser
import com.progressterra.ipbandroidview.shared.ui.statebox.ScreenState
import com.progressterra.ipbandroidview.shared.ui.statebox.StateBoxEvent
import com.progressterra.ipbandroidview.shared.ui.textfield.TextFieldEvent

class OrderDetailsScreenViewModel(
    private val orderDetailsUseCase: OrderDetailsUseCase,
    private val fetchOrderChatUseCase: FetchOrderChatUseCase,
    fetchMessagesUseCase: FetchMessagesUseCase,
    sendMessageUseCase: SendMessageUseCase
) : BaseViewModel<OrderDetailsScreenState, OrderDetailsScreenEvent>(), UseOrderDetailsScreen {

    override fun createInitialState() = OrderDetailsScreenState()

    private val attachableChatModule = AttachableChatModule(
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

    fun setupId(newId: String) {
        emitState {
            it.copy(details = it.details.copy(id = newId))
        }
        refresh()
    }

    fun refresh() {
        onBackground {
            emitState { it.copy(screen = it.screen.copy(state = ScreenState.LOADING)) }
            var isSuccess = true
            orderDetailsUseCase(currentState.details.id).onSuccess { details ->
                emitState { it.copy(details = details) }
            }.onFailure {
                isSuccess = false
            }
            fetchOrderChatUseCase(
                currentState.details.id,
                currentState.details.number
            ).onSuccess { dialogId ->
                attachableChatModule.setup(dialogId)
                attachableChatModule.refresh()
            }.onFailure {
                isSuccess = false
            }
            emitState { it.copy(screen = it.screen.copy(state = isSuccess.toScreenState())) }
        }
    }

    override fun handle(event: StateBoxEvent) {
        refresh()
    }

    override fun handle(event: OrderCardEvent) {
        postEffect(OrderDetailsScreenEvent.OpenGoods(event.id))
    }

    override fun handle(event: OrderDetailsEvent) {
        when (event) {
            is OrderDetailsEvent.Tracking -> postEffect(
                OrderDetailsScreenEvent.Tracking(currentState.details.toOrderTrackingState())
            )

            is OrderDetailsEvent.Chat -> attachableChatModule.open()
        }
    }

    override fun handle(event: TextFieldEvent) {
        attachableChatModule.handle(event)
    }

    override fun handle(event: AttachableChatEvent) {
        attachableChatModule.handle(event)
    }

    override fun handle(event: TopBarEvent) {
        postEffect(OrderDetailsScreenEvent.Back)
    }
}