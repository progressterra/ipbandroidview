package com.progressterra.ipbandroidview.ui.order

import android.util.Log
import androidx.lifecycle.ViewModel
import com.progressterra.ipbandroidview.model.PaymentType
import com.progressterra.ipbandroidview.core.ScreenState
import com.progressterra.ipbandroidview.domain.usecase.bonus.UseBonusesUseCase
import com.progressterra.ipbandroidview.domain.usecase.delivery.AvailableDeliveryUseCase
import com.progressterra.ipbandroidview.domain.usecase.delivery.PaymentMethodsForDeliveryUseCase
import com.progressterra.ipbandroidview.domain.usecase.order.ConfirmOrderUseCase
import com.progressterra.ipbandroidview.domain.usecase.user.FetchUserAddressUseCase
import com.progressterra.ipbandroidview.model.DeliveryMethod
import com.progressterra.ipbandroidview.model.OrderGoods
import org.orbitmvi.orbit.Container
import org.orbitmvi.orbit.ContainerHost
import org.orbitmvi.orbit.syntax.simple.intent
import org.orbitmvi.orbit.syntax.simple.postSideEffect
import org.orbitmvi.orbit.syntax.simple.reduce
import org.orbitmvi.orbit.viewmodel.container

class OrderViewModel(
    private val useBonusesUseCase: UseBonusesUseCase,
    private val notUseBonusesUseCase: UseBonusesUseCase,
    private val availableDeliveryUseCase: AvailableDeliveryUseCase,
    private val confirmOrderUseCase: ConfirmOrderUseCase,
    private val fetchUserAddressUseCase: FetchUserAddressUseCase,
    private val paymentMethodsForDeliveryUseCase: PaymentMethodsForDeliveryUseCase
) : ViewModel(), ContainerHost<OrderState, OrderEffect> {

    override val container: Container<OrderState, OrderEffect> = container(OrderState())

    fun refresh() = intent {
        reduce { state.copy(screenState = ScreenState.LOADING) }
        availableDeliveryUseCase.deliveries().onSuccess { deliveryMethods ->
            Log.d("DELIVERY", "delivery methods: $deliveryMethods")
            reduce { state.copy(deliveryMethods = deliveryMethods) }
            fetchUserAddressUseCase.fetch().onSuccess {
                Log.d("DELIVERY", "address: $it")
                reduce { state.copy(address = it, screenState = ScreenState.SUCCESS) }
            }.onFailure {
                reduce { state.copy(screenState = ScreenState.ERROR) }
            }
        }.onFailure {
            reduce { state.copy(screenState = ScreenState.ERROR) }
        }
    }

    fun setCart(goods: List<OrderGoods>) = intent {
        reduce {
            state.copy(goods = goods)
        }
        refresh()
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
        paymentMethodsForDeliveryUseCase.methods(method).onSuccess {
            reduce { state.copy(paymentMethods = it) }
        }
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
        if (use) notUseBonusesUseCase.use(state.availableBonuses).onSuccess {
            reduce { state.copy(useBonuses = !state.useBonuses) }
        }
        else useBonusesUseCase.use(state.availableBonuses).onSuccess {
            reduce { state.copy(useBonuses = !state.useBonuses) }
        }
    }

    fun editPromoCode(code: String) = intent {
        reduce { state.copy(promoCodeName = code) }
    }

    fun applyPromoCode() = intent {

    }

    fun changeReceiveReceipt(receive: Boolean) = intent {
        reduce { state.copy(receiveReceipt = receive) }
    }

    fun editEmail(email: String) = intent {
        reduce { state.copy(email = email) }
    }

    fun payment() = intent {
        confirmOrderUseCase.confirm().onSuccess {
            postSideEffect(OrderEffect.Next(it))
        }
    }

    fun openUrl(url: String) = intent { }
}