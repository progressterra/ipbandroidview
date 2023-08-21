package com.progressterra.ipbandroidview.pages.orderdetails

import com.progressterra.ipbandroidapi.api.cart.models.TypeStatusOrder
import com.progressterra.ipbandroidview.entities.SimplePrice
import com.progressterra.ipbandroidview.features.attachablechat.AttachableChatEvent
import com.progressterra.ipbandroidview.features.attachablechat.AttachableChatState
import com.progressterra.ipbandroidview.features.ordercard.OrderCardEvent
import com.progressterra.ipbandroidview.features.orderdetails.OrderDetailsEvent
import com.progressterra.ipbandroidview.features.orderdetails.OrderDetailsState
import com.progressterra.ipbandroidview.features.topbar.TopBarEvent
import com.progressterra.ipbandroidview.pages.support.FetchMessagesUseCase
import com.progressterra.ipbandroidview.pages.support.SendMessageUseCase
import com.progressterra.ipbandroidview.shared.BaseViewModel
import com.progressterra.ipbandroidview.shared.ScreenState
import com.progressterra.ipbandroidview.shared.ui.statebox.StateBoxEvent
import com.progressterra.ipbandroidview.shared.ui.textfield.TextFieldEvent
import com.progressterra.ipbandroidview.shared.ui.textfield.TextFieldState
import com.progressterra.ipbandroidview.shared.ui.textfield.TextInputType
import com.progressterra.ipbandroidview.widgets.messages.MessagesState
import com.progressterra.ipbandroidview.widgets.orderitems.OrderItemsState
import kotlinx.coroutines.flow.emptyFlow

class OrderDetailsScreenViewModel(
    private val orderDetailsUseCase: OrderDetailsUseCase,
    private val fetchOrderChatUseCase: FetchOrderChatUseCase,
    private val fetchMessagesUseCase: FetchMessagesUseCase,
    private val sendMessageUseCase: SendMessageUseCase
) : BaseViewModel<OrderDetailsScreenState, OrderDetailsScreenEvent>(), UseOrderDetailsScreen {

    override fun createInitialState() = OrderDetailsScreenState(
        details = OrderDetailsState(
            id = "",
            number = "",
            status = TypeStatusOrder.CANCELED,
            date = "",
            count = 0,
            totalPrice = SimplePrice(),
            goods = OrderItemsState(
                items = emptyList()
            )
        ),
        id = "",
        dialogId = "",
        chat = AttachableChatState(
            messagesState = MessagesState(
                items = emptyFlow()
            ),
            input = TextFieldState(id = "input", type = TextInputType.CHAT),
            isVisible = false
        ),
        screenState = ScreenState.LOADING
    )


    fun setupId(newId: String) {
        emitState {
            it.copy(id = newId)
        }
        refresh()
    }

    fun refresh() {
        onBackground {
            emitState { it.copy(screenState = ScreenState.LOADING) }
            orderDetailsUseCase(currentState.id).onSuccess { details ->
                emitState {
                    it.copy(details = details, screenState = ScreenState.SUCCESS)
                }
            }.onFailure {
                emitState {
                    it.copy(screenState = ScreenState.ERROR)
                }
            }
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
                OrderDetailsScreenEvent.Tracking(
                    currentState.details.toOrderTrackingState()
                )
            )

            is OrderDetailsEvent.Chat -> onBackground {
                fetchOrderChatUseCase(
                    currentState.id,
                    currentState.details.number
                ).onSuccess { dialogId ->
                    fetchMessagesUseCase(dialogId).onSuccess { messages ->
                        emitState {
                            it.copy(
                                chat = it.chat.copy(
                                    messagesState = it.chat.messagesState.copy(
                                        items = cachePaging(
                                            messages
                                        )
                                    ), isVisible = true
                                ), dialogId = dialogId
                            )
                        }
                    }
                }
            }
        }
    }

    override fun handle(event: TextFieldEvent) {
        when (event) {
            is TextFieldEvent.TextChanged -> emitState {
                it.copy(
                    chat = it.chat.copy(
                        input = it.chat.input.copy(
                            text = event.text
                        )
                    )
                )
            }

            else -> onBackground {
                sendMessageUseCase(
                    currentState.dialogId,
                    currentState.chat.input.text
                ).onSuccess {
                    emitState {
                        it.copy(
                            chat = it.chat.copy(input = it.chat.input.copy(text = ""))
                        )
                    }
                    fetchMessagesUseCase(currentState.dialogId).onSuccess { newMessages ->
                        emitState {
                            it.copy(
                                chat = it.chat.copy(
                                    messagesState = it.chat.messagesState.copy(
                                        items = cachePaging(newMessages)
                                    )
                                )
                            )
                        }
                    }
                }
            }
        }
    }

    override fun handle(event: AttachableChatEvent) {
        emitState {
            it.copy(chat = it.chat.copy(isVisible = false))
        }
    }

    override fun handle(event: TopBarEvent) {
        postEffect(OrderDetailsScreenEvent.Back)
    }
}