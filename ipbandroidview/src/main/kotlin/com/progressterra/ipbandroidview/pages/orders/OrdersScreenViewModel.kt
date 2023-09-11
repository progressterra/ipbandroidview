package com.progressterra.ipbandroidview.pages.orders

import com.progressterra.ipbandroidview.features.ordercompact.OrderCompactEvent
import com.progressterra.ipbandroidview.features.topbar.TopBarEvent
import com.progressterra.ipbandroidview.shared.mvi.AbstractNonInputViewModel
import com.progressterra.ipbandroidview.shared.ui.statecolumn.ScreenState
import com.progressterra.ipbandroidview.shared.ui.statecolumn.StateColumnEvent

class OrdersScreenViewModel(
    private val ordersUseCase: OrdersUseCase
) : AbstractNonInputViewModel<OrdersScreenState, OrdersScreenEffect>(), UseOrdersScreen {

    override fun createInitialState() = OrdersScreenState()

    override fun refresh() {
        onBackground {
            emitState { createInitialState() }
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
        postEffect(OrdersScreenEffect.OpenDetails(event.state.id))
    }

    override fun handle(event: TopBarEvent) {
        postEffect(OrdersScreenEffect.Back)
    }

    override fun handle(event: StateColumnEvent) {
        refresh()
    }
}