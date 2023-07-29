package com.progressterra.ipbandroidview.pages.orderdetails

import com.progressterra.ipbandroidview.features.ordercard.OrderCardEvent
import com.progressterra.ipbandroidview.features.orderdetails.OrderDetailsEvent
import com.progressterra.ipbandroidview.features.topbar.TopBarEvent
import com.progressterra.ipbandroidview.shared.BaseViewModel
import com.progressterra.ipbandroidview.shared.ScreenState
import com.progressterra.ipbandroidview.shared.ui.statebox.StateBoxEvent

class OrderDetailsScreenViewModel(
    private val orderDetailsUseCase: OrderDetailsUseCase
) : BaseViewModel<OrderDetailsScreenState, OrderDetailsScreenEvent>(), UseOrderDetailsScreen {

    override val initialState = OrderDetailsScreenState()


    fun setupId(newId: String) {
        fastEmitState {
            it.copy(id = newId)
        }
    }

    fun refresh() {
        onBackground {
            emitState {
                it.copy(screenState = ScreenState.LOADING)
            }

            orderDetailsUseCase(state.value.id).onSuccess { details ->
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
        postEffect(OrderDetailsScreenEvent.Tracking(state.value.details.toOrderTrackingState()))
    }

    override fun handle(event: TopBarEvent) {
        postEffect(OrderDetailsScreenEvent.Back)
    }
}