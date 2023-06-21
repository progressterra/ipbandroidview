package com.progressterra.ipbandroidview.pages.orderlist

import androidx.lifecycle.ViewModel
import com.progressterra.ipbandroidview.entities.toOrderDetailsState
import com.progressterra.ipbandroidview.features.ordercompact.OrderCompactEvent
import com.progressterra.ipbandroidview.features.topbar.TopBarEvent
import com.progressterra.ipbandroidview.shared.ScreenState
import com.progressterra.ipbandroidview.shared.ui.statebox.StateBoxEvent
import org.orbitmvi.orbit.Container
import org.orbitmvi.orbit.ContainerHost
import org.orbitmvi.orbit.syntax.simple.intent
import org.orbitmvi.orbit.syntax.simple.postSideEffect
import org.orbitmvi.orbit.syntax.simple.reduce
import org.orbitmvi.orbit.viewmodel.container

class OrdersListViewModel(
    private val ordersUseCase: OrdersUseCase
) : ViewModel(), ContainerHost<OrdersListState, OrdersListEvent>, UseOrdersList {

    override val container: Container<OrdersListState, OrdersListEvent> =
        container(OrdersListState())

    fun refresh() {
        intent {
            reduce { state.copy(screenState = ScreenState.LOADING) }
            ordersUseCase().onSuccess {
                reduce { state.copy(screenState = ScreenState.SUCCESS, orders = it) }
            }.onFailure {
                reduce { state.copy(screenState = ScreenState.ERROR) }
            }
        }
    }

    override fun handle(event: OrderCompactEvent) {
        intent {
            postSideEffect(OrdersListEvent.OpenDetails(event.state.toOrderDetailsState()))
        }
    }

    override fun handle(event: TopBarEvent) {
        intent {
            postSideEffect(OrdersListEvent.Back)
        }
    }

    override fun handle(event: StateBoxEvent) {
        intent {
            refresh()
        }
    }
}