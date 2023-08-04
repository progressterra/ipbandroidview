package com.progressterra.ipbandroidview.pages.orderdetails

import com.progressterra.ipbandroidview.features.ordercard.OrderCardEvent
import com.progressterra.ipbandroidview.features.orderchat.OrderChatEvent
import com.progressterra.ipbandroidview.features.orderdetails.OrderDetailsEvent
import com.progressterra.ipbandroidview.features.topbar.TopBarEvent
import com.progressterra.ipbandroidview.pages.support.FetchMessagesUseCase
import com.progressterra.ipbandroidview.pages.support.SendMessageUseCase
import com.progressterra.ipbandroidview.shared.BaseViewModel
import com.progressterra.ipbandroidview.shared.ScreenState
import com.progressterra.ipbandroidview.shared.ui.statebox.StateBoxEvent
import com.progressterra.ipbandroidview.shared.ui.textfield.TextFieldEvent

class OrderDetailsScreenViewModel(
    private val orderDetailsUseCase: OrderDetailsUseCase,
    private val fetchOrderChatUseCase: FetchOrderChatUseCase,
    private val fetchMessagesUseCase: FetchMessagesUseCase,
    private val sendMessageUseCase: SendMessageUseCase
) : BaseViewModel<OrderDetailsScreenState, OrderDetailsScreenEvent>(), UseOrderDetailsScreen {

    override fun createInitialState() = OrderDetailsScreenState()


    fun setupId(newId: String) {
        emitState {
            it.copy(id = newId)
        }
    }

    fun refresh() {
        onBackground {
            emitState {
                it.copy(screenState = ScreenState.LOADING)
            }

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
                fetchOrderChatUseCase(currentState.id).onSuccess { dialogId ->
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

    override fun handle(event: OrderChatEvent) {
        emitState {
            it.copy(chat = it.chat.copy(isVisible = false))
        }
    }

    override fun handle(event: TopBarEvent) {
        postEffect(OrderDetailsScreenEvent.Back)
    }
}