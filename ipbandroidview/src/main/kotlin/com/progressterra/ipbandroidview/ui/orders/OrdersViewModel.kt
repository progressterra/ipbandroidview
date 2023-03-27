package com.progressterra.ipbandroidview.ui.orders

import androidx.lifecycle.ViewModel
import com.progressterra.ipbandroidview.core.ScreenState
import com.progressterra.ipbandroidview.processes.usecase.store.OrdersUseCase
import org.orbitmvi.orbit.Container
import org.orbitmvi.orbit.ContainerHost
import org.orbitmvi.orbit.syntax.simple.intent
import org.orbitmvi.orbit.syntax.simple.postSideEffect
import org.orbitmvi.orbit.syntax.simple.reduce
import org.orbitmvi.orbit.viewmodel.container

class OrdersViewModel(
    private val ordersUseCase: OrdersUseCase
) : ViewModel(),
    ContainerHost<OrdersState, OrdersEffect>, OrdersInteractor {

    override val container: Container<OrdersState, OrdersEffect> =
        container(OrdersState())

    override fun openGoodsDetails(goodsId: String) =
        intent { postSideEffect(OrdersEffect.GoodsDetails(goodsId)) }

    override fun onBack() = intent { postSideEffect(OrdersEffect.Back) }

    override fun refresh() = intent {
        reduce { state.copy(screenState = ScreenState.LOADING) }
        ordersUseCase().onSuccess {
            reduce { state.copy(screenState = ScreenState.SUCCESS, orders = it) }
        }.onFailure {
            reduce { state.copy(screenState = ScreenState.ERROR) }
        }
    }
}