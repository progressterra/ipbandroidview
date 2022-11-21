package com.progressterra.ipbandroidview.ui.order

import androidx.lifecycle.ViewModel
import com.progressterra.ipbandroidview.composable.component.PaymentType
import com.progressterra.ipbandroidview.domain.usecase.store.UseBonusesUseCase
import com.progressterra.ipbandroidview.model.Cart
import com.progressterra.ipbandroidview.model.DeliveryMethod
import org.orbitmvi.orbit.Container
import org.orbitmvi.orbit.ContainerHost
import org.orbitmvi.orbit.syntax.simple.intent
import org.orbitmvi.orbit.syntax.simple.postSideEffect
import org.orbitmvi.orbit.syntax.simple.reduce
import org.orbitmvi.orbit.viewmodel.container

class OrderViewModel(
    private val useBonusesUseCase: UseBonusesUseCase,
    private val notUseBonusesUseCase: UseBonusesUseCase
) : ViewModel(), ContainerHost<OrderState, OrderEffect> {

    override val container: Container<OrderState, OrderEffect> = container(OrderState())

    fun setCart(cart: Cart) = intent {
        reduce {
            state.copy(cart = cart)
        }
    }

    fun back() = intent {
        postSideEffect(OrderEffect.Back)
    }

    fun goodsDetails(goodsId: String) = intent {
        postSideEffect(OrderEffect.GoodsDetails(goodsId))
    }

    fun changeAddress() = intent {
        postSideEffect(OrderEffect.City)
    }

    fun selectPickUpPoint() = intent {
        postSideEffect(OrderEffect.PickUp)
    }

    fun selectDeliveryMethod(method: DeliveryMethod) = intent {
        reduce { state.copy(selectedDeliveryMethod = method) }
    }

    fun selectPayment(payment: PaymentType) = intent {
        reduce { state.copy(currentPaymentMethod = payment) }
    }

    fun editApartment(apartment: String) = intent {
        reduce { state.copy(apartment = apartment) }
    }

    fun editComment(comment: String) = intent {
        reduce { state.copy(comment = comment) }
    }

    fun editEntryway(entryway: String) = intent {
        reduce { state.copy(entryway = entryway) }
    }

    fun changeUseBonuses(use: Boolean) = intent {
        reduce { state.copy(useBonuses = use) }
    }

    fun editPromoCode(code: String) = intent {
        reduce { state.copy(promoCode = code) }
    }

    fun applyPromoCode() = intent { }

    fun changeReceiveReceipt(receive: Boolean) = intent {
        reduce { state.copy(receiveReceipt = receive) }

    }

    fun editEmail(email: String) = intent {
        reduce { state.copy(email = email) }

    }

    fun payment() = intent { }

    fun openUrl(url: String) = intent { }

    fun refresh() = intent { }
}