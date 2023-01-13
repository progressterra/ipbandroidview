package com.progressterra.ipbandroidview.ui.order

import androidx.lifecycle.ViewModel
import com.progressterra.ipbandroidview.core.ScreenState
import com.progressterra.ipbandroidview.domain.usecase.bonus.AvailableBonusesUseCase
import com.progressterra.ipbandroidview.domain.usecase.bonus.UseBonusesUseCase
import com.progressterra.ipbandroidview.domain.usecase.delivery.AvailableDeliveryUseCase
import com.progressterra.ipbandroidview.domain.usecase.delivery.PaymentMethodsUseCase
import com.progressterra.ipbandroidview.domain.usecase.delivery.SetDeliveryAddressUseCase
import com.progressterra.ipbandroidview.domain.usecase.order.ConfirmOrderUseCase
import com.progressterra.ipbandroidview.domain.usecase.user.FetchUserAddressUseCase
import com.progressterra.ipbandroidview.domain.usecase.user.FetchUserEmailUseCase
import com.progressterra.ipbandroidview.model.delivery.Delivery
import com.progressterra.ipbandroidview.model.delivery.DeliveryType
import com.progressterra.ipbandroidview.model.delivery.PickUpPointInfo
import com.progressterra.ipbandroidview.model.payment.PaymentType
import com.progressterra.ipbandroidview.model.store.OrderGoods
import com.progressterra.ipbandroidview.model.store.SimplePrice
import org.orbitmvi.orbit.Container
import org.orbitmvi.orbit.ContainerHost
import org.orbitmvi.orbit.annotation.OrbitExperimental
import org.orbitmvi.orbit.syntax.simple.blockingIntent
import org.orbitmvi.orbit.syntax.simple.intent
import org.orbitmvi.orbit.syntax.simple.postSideEffect
import org.orbitmvi.orbit.syntax.simple.reduce
import org.orbitmvi.orbit.viewmodel.container

@OptIn(OrbitExperimental::class)
class OrderViewModel(
    private val useBonusesUseCase: UseBonusesUseCase,
    private val notUseBonusesUseCase: UseBonusesUseCase,
    private val bonusesUseCase: AvailableBonusesUseCase,
    private val availableDeliveryUseCase: AvailableDeliveryUseCase,
    private val confirmOrderUseCase: ConfirmOrderUseCase,
    private val fetchUserAddressUseCase: FetchUserAddressUseCase,
    private val fetchUserEmailUseCase: FetchUserEmailUseCase,
    private val paymentMethodsUseCase: PaymentMethodsUseCase,
    private val setDeliveryAddressUseCase: SetDeliveryAddressUseCase
) : ViewModel(), ContainerHost<OrderState, OrderEffect>, OrderInteractor {

    override val container: Container<OrderState, OrderEffect> = container(OrderState())

    init {
        refresh()
    }

    override fun refresh() = intent {
        reduce { state.copy(screenState = ScreenState.LOADING) }
        bonusesUseCase().mapCatching { bonuses ->
            reduce { state.copy(availableBonuses = bonuses) }
            fetchUserEmailUseCase().getOrThrow()
        }.mapCatching { email ->
            reduce { state.copy(email = email) }
            availableDeliveryUseCase().getOrThrow()
        }.mapCatching { deliveryMethods ->
            reduce { state.copy(deliveryMethods = deliveryMethods) }
            fetchUserAddressUseCase().getOrThrow()
        }.mapCatching { address ->
            reduce { state.copy(addressUI = address) }
            paymentMethodsUseCase().getOrThrow()
        }.onSuccess { paymentMethods ->
            reduce {
                state.copy(
                    paymentMethods = paymentMethods,
                    screenState = ScreenState.SUCCESS
                )
            }
        }.onFailure { reduce { state.copy(screenState = ScreenState.ERROR) } }
    }

    fun setCart(goods: List<OrderGoods>) = intent {
        reduce {
            state.copy(goods = goods)
        }
        recalculate()
    }

    override fun onBack() = intent {
        postSideEffect(OrderEffect.Back)
    }

    override fun onGoodsDetails(goodsId: String) = intent {
        postSideEffect(OrderEffect.GoodsDetails(goodsId))
    }

    override fun changeAddress() = intent {
        postSideEffect(OrderEffect.City)
    }

    override fun selectPickUpPoint() = intent {
        val pickUpPoints =
            (state.deliveryMethods[DeliveryType.PICK_UP_POINT] as Delivery.PickUpPointDelivery).points
        postSideEffect(OrderEffect.PickUp(pickUpPoints))
    }

    fun updatePickUpPoint(pickUpPointInfo: PickUpPointInfo) = intent {
        val newDelivery =
            (state.selectedDeliveryMethod as Delivery.PickUpPointDelivery).copy(currentPoint = pickUpPointInfo)
        val newDeliveries = state.deliveryMethods.toMutableMap()
        newDeliveries[DeliveryType.PICK_UP_POINT] = newDelivery
        reduce { state.copy(selectedDeliveryMethod = newDelivery, deliveryMethods = newDeliveries) }
        recalculate()
        checkPaymentAvailability()
    }

    override fun selectDeliveryMethod(delivery: Delivery) = intent {
        reduce { state.copy(selectedDeliveryMethod = delivery) }
        recalculate()
        checkPaymentAvailability()
    }

    override fun selectPayment(paymentType: PaymentType) = intent {
        reduce { state.copy(selectedPaymentMethod = paymentType) }
        checkPaymentAvailability()
    }

    override fun editComment(comment: String) = blockingIntent {
        val selectedMethod = state.selectedDeliveryMethod
        if (selectedMethod is Delivery.CourierDelivery) {
            reduce {
                state.copy(selectedDeliveryMethod = selectedMethod.copy(commentary = comment))
            }
        }
    }

    override fun changeUseBonuses(useBonuses: Boolean) = intent {
        if (useBonuses) notUseBonusesUseCase(state.availableBonuses.quantity).onSuccess {
            reduce { state.copy(useBonuses = !state.useBonuses) }
        }
        else useBonusesUseCase(state.availableBonuses.quantity).onSuccess {
            reduce { state.copy(useBonuses = !state.useBonuses) }
        }
    }

    override fun editPromoCode(promoCode: String) = blockingIntent {
        reduce { state.copy(promoCodeName = promoCode) }
    }

    override fun applyPromoCode() = intent {
        //TODO
    }

    override fun changeReceiveReceipt(receiveReceive: Boolean) = intent {
        reduce { state.copy(receiveReceipt = receiveReceive) }
    }

    override fun editEmail(email: String) = blockingIntent {
        reduce { state.copy(email = email) }
    }

    override fun payment() = intent {
        state.selectedDeliveryMethod?.let { deliveryMethod ->
            setDeliveryAddressUseCase(deliveryMethod, state.addressUI).onSuccess {
                confirmOrderUseCase().onSuccess { postSideEffect(OrderEffect.Next(it)) }
            }
        }
    }

    override fun openUrl(url: String) = intent { }

    private fun checkPaymentAvailability() = intent {
        reduce { state.copy(paymentReady = state.selectedPaymentMethod != null && state.selectedDeliveryMethod != null) }
    }

    private fun recalculate() = intent {
        var totalPrice = SimplePrice()
        state.goods.forEach {
            totalPrice += it.totalPrice
        }
        state.selectedDeliveryMethod?.price?.let {
            totalPrice += it
        }
        reduce { state.copy(totalPrice = totalPrice) }
    }
}