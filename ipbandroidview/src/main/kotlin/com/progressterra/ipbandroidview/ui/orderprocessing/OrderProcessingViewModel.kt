package com.progressterra.ipbandroidview.ui.orderprocessing

import androidx.lifecycle.ViewModel
import com.progressterra.ipbandroidview.model.OrderResult
import org.orbitmvi.orbit.Container
import org.orbitmvi.orbit.ContainerHost
import org.orbitmvi.orbit.syntax.simple.intent
import org.orbitmvi.orbit.syntax.simple.postSideEffect
import org.orbitmvi.orbit.syntax.simple.reduce
import org.orbitmvi.orbit.viewmodel.container

class OrderProcessingViewModel : ViewModel(),
    ContainerHost<OrderProcessingState, OrderProcessingEffect>, OrderProcessingInteractor {

    override val container: Container<OrderProcessingState, OrderProcessingEffect> =
        container(OrderProcessingState())

    fun setOrderResult(result: OrderResult) = intent { reduce { state.copy(orderResult = result) } }

    override fun onNext() = intent { postSideEffect(OrderProcessingEffect.Next) }

    override fun onBack() = intent { postSideEffect(OrderProcessingEffect.Back) }
}