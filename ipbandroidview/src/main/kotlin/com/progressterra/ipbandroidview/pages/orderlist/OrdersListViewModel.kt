package com.progressterra.ipbandroidview.pages.orderlist

import com.progressterra.ipbandroidview.features.ordercompact.OrderCompactEvent
import com.progressterra.ipbandroidview.features.topbar.TopBarEvent
import com.progressterra.ipbandroidview.shared.mvi.AbstractViewModel
import com.progressterra.ipbandroidview.shared.ui.statecolumn.ScreenState
import com.progressterra.ipbandroidview.shared.ui.statecolumn.StateColumnEvent

class OrdersListViewModel(
    private val ordersUseCase: OrdersUseCase
) : AbstractViewModel<OrdersListState, OrdersListEvent>(), UseOrdersList {

    override fun createInitialState() = OrdersListState()

    fun refresh() {
        onBackground {
            emitState { it.copy(screen = it.screen.copy(state = ScreenState.LOADING)) }
            ordersUseCase().onSuccess { nonCached ->
                emitState {
                    it.copy(
                        screen = it.screen.copy(state = ScreenState.SUCCESS),
                        orders = cachePaging(nonCached)
                    )
                }
            }.onFailure {
                emitState { it.copy(screen = it.screen.copy(state = ScreenState.ERROR)) }
            }
        }
    }

    override fun handle(event: OrderCompactEvent) {
        postEffect(OrdersListEvent.OpenDetails(event.state.id))
    }

    override fun handle(event: TopBarEvent) {
        postEffect(OrdersListEvent.Back)
    }

    override fun handle(event: StateColumnEvent) {
        refresh()
    }
}