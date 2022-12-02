package com.progressterra.ipbandroidview.ui.orders

import androidx.lifecycle.ViewModel
import com.progressterra.ipbandroidview.core.ScreenState
import com.progressterra.ipbandroidview.domain.usecase.store.OrdersUseCase
import org.orbitmvi.orbit.Container
import org.orbitmvi.orbit.ContainerHost
import org.orbitmvi.orbit.syntax.simple.intent
import org.orbitmvi.orbit.syntax.simple.postSideEffect
import org.orbitmvi.orbit.syntax.simple.reduce
import org.orbitmvi.orbit.viewmodel.container

class OrdersViewModel(
    private val ordersUseCase: OrdersUseCase
) : ViewModel(),
    ContainerHost<OrdersState, OrdersEffect> {

    override val container: Container<OrdersState, OrdersEffect> =
        container(OrdersState())

    fun openDetails(item: String) =
        intent { postSideEffect(OrdersEffect.GoodsDetails(item)) }

    fun back() = intent { postSideEffect(OrdersEffect.Back) }

    fun refresh() = intent {
        reduce { state.copy(screenState = ScreenState.LOADING) }
        ordersUseCase().onSuccess {
            reduce { state.copy(screenState = ScreenState.SUCCESS, orders = it) }
        }.onFailure {
            reduce { state.copy(screenState = ScreenState.ERROR) }
        }
    }
}