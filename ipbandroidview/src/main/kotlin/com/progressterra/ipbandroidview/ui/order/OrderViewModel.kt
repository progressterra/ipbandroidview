package com.progressterra.ipbandroidview.ui.order

import androidx.lifecycle.ViewModel
import com.progressterra.ipbandroidview.processes.usecase.OpenUrlUseCase
import com.progressterra.ipbandroidview.processes.usecase.bonus.ProshkaBonusesUseCase
import com.progressterra.ipbandroidview.processes.usecase.bonus.UseBonusesUseCase
import com.progressterra.ipbandroidview.widgets.deliverypicker.FetchAvailableDeliveryUseCase
import com.progressterra.ipbandroidview.processes.usecase.delivery.PaymentMethodsUseCase
import com.progressterra.ipbandroidview.widgets.deliverypicker.SetDeliveryAddressUseCase
import com.progressterra.ipbandroidview.processes.usecase.order.ConfirmOrderUseCase
import com.progressterra.ipbandroidview.processes.usecase.user.FetchUserAddressUseCase
import com.progressterra.ipbandroidview.processes.usecase.user.FetchUserEmailUseCase
import org.orbitmvi.orbit.Container
import org.orbitmvi.orbit.ContainerHost
import org.orbitmvi.orbit.annotation.OrbitExperimental
import org.orbitmvi.orbit.syntax.simple.intent
import org.orbitmvi.orbit.viewmodel.container

@OptIn(OrbitExperimental::class)
class OrderViewModel(
    private val useBonusesUseCase: UseBonusesUseCase,
    private val notUseBonusesUseCase: UseBonusesUseCase,
    private val bonusesUseCase: ProshkaBonusesUseCase,
    private val availableDeliveryUseCase: FetchAvailableDeliveryUseCase,
    private val confirmOrderUseCase: ConfirmOrderUseCase,
    private val fetchUserAddressUseCase: FetchUserAddressUseCase,
    private val fetchUserEmailUseCase: FetchUserEmailUseCase,
    private val paymentMethodsUseCase: PaymentMethodsUseCase,
    private val setDeliveryAddressUseCase: SetDeliveryAddressUseCase,
    private val openUrlUseCase: OpenUrlUseCase
) : ViewModel(), ContainerHost<OrderState, OrderEffect> {

    override val container: Container<OrderState, OrderEffect> = container(OrderState())

    init {
//        refresh()
    }

//    override fun refresh() = intent {
//        reduce { state.copy(screenState = ScreenState.LOADING) }
//        bonusesUseCase().mapCatching { bonuses ->
//            reduce { state.copy(availableBonuses = bonuses) }
//            fetchUserEmailUseCase()
//        }.mapCatching { email ->
//            reduce { state.copy(email = email) }
//            availableDeliveryUseCase().getOrThrow()
//        }.mapCatching { deliveryMethods ->
//            reduce { state.copy(deliveryMethods = deliveryMethods) }
//            fetchUserAddressUseCase().getOrThrow()
//        }.mapCatching { address ->
//            reduce { state.copy(addressUI = address) }
//            paymentMethodsUseCase().getOrThrow()
//        }.onSuccess { paymentMethods ->
//            reduce {
//                state.copy(
//                    paymentMethods = paymentMethods, screenState = ScreenState.SUCCESS
//                )
//            }
//        }.onFailure { reduce { state.copy(screenState = ScreenState.ERROR) } }
//    }
//
//    fun setCart(goods: List<OrderGoods>) = intent {
//        reduce {
//            state.copy(goods = goods)
//        }
//        recalculate()
//    }
//
//    override fun onBack() = intent {
//        postSideEffect(OrderEffect.Back)
//    }
//
//    override fun onGoodsDetails(goodsId: String) = intent {
//        postSideEffect(OrderEffect.GoodsDetails(goodsId))
//    }
//
//    override fun changeAddress() = intent {
//        postSideEffect(OrderEffect.City)
//    }
//
//    override fun selectPickUpPoint() = intent {
//        val pickUpPoints =
//            (state.deliveryMethods[DeliveryType.PICK_UP_POINT] as Delivery.PickUpPointDelivery).points
//        postSideEffect(OrderEffect.PickUp(pickUpPoints))
//    }
//
//    fun updatePickUpPoint(pickUpPointInfo: PickUpPointInfo) = intent {
//        val newDelivery =
//            (state.selectedDeliveryMethod as Delivery.PickUpPointDelivery).copy(currentPoint = pickUpPointInfo)
//        val newDeliveries = state.deliveryMethods.toMutableMap()
//        newDeliveries[DeliveryType.PICK_UP_POINT] = newDelivery
//        reduce { state.copy(selectedDeliveryMethod = newDelivery, deliveryMethods = newDeliveries) }
//        recalculate()
//        checkPaymentAvailability()
//    }
//
//    override fun selectDeliveryMethod(delivery: Delivery) = intent {
//        reduce { state.copy(selectedDeliveryMethod = delivery) }
//        recalculate()
//        checkPaymentAvailability()
//    }
//
//    override fun selectPayment(paymentType: PaymentType) = intent {
//        reduce { state.copy(selectedPaymentMethod = paymentType) }
//        checkPaymentAvailability()
//    }
//
//    override fun editComment(comment: String) = blockingIntent {
//        val selectedMethod = state.selectedDeliveryMethod
//        if (selectedMethod is Delivery.CourierDelivery) {
//            reduce {
//                state.copy(selectedDeliveryMethod = selectedMethod.copy(commentary = comment))
//            }
//        }
//    }
//
//    override fun changeUseBonuses(useBonuses: Boolean) = intent {
//        if (useBonuses) notUseBonusesUseCase(state.availableBonuses.bonuses.toInt()).onSuccess {
//            reduce { state.copy(useBonuses = !state.useBonuses) }
//        }
//        else useBonusesUseCase(state.availableBonuses.bonuses.toInt()).onSuccess {
//            reduce { state.copy(useBonuses = !state.useBonuses) }
//        }
//    }
//
//    override fun changeReceiveReceipt(receiveReceive: Boolean) = intent {
//        reduce { state.copy(receiveReceipt = receiveReceive) }
//    }
//
//    override fun editEmail(email: String) = blockingIntent {
//        reduce { state.copy(email = email) }
//    }
//
//    override fun handle(event: ReceiptComponentEvent) = when (event) {
//        is ReceiptComponentEvent.OpenUrl -> openUrl(event.url)
//        is ReceiptComponentEvent.Payment -> payment()
//    }
//
//    private fun payment() = intent {
//        state.selectedDeliveryMethod?.let { deliveryMethod ->
//            setDeliveryAddressUseCase(deliveryMethod, state.addressUI).onSuccess {
//                confirmOrderUseCase().onSuccess { postSideEffect(OrderEffect.Next(it)) }
//            }
//        }
//    }

    private fun openUrl(url: String) = intent { openUrlUseCase(url) }

//    private fun checkPaymentAvailability() = intent {
//        val newReceiptComponentState = state.receiptComponentState.copy(
//            paymentReady = state.selectedPaymentMethod != null && state.selectedDeliveryMethod != null
//        )
//        reduce { state.copy(receiptComponentState = newReceiptComponentState) }
//    }
//
//    private fun recalculate() = intent {
//        var totalPrice = SimplePrice()
//        state.goods.forEach {
//            totalPrice += it.totalPrice
//        }
//        state.selectedDeliveryMethod?.price?.let {
//            totalPrice += it
//        }
//        val newReceiptComponentState = state.receiptComponentState.copy(totalPrice = totalPrice)
//        reduce { state.copy(receiptComponentState = newReceiptComponentState) }
//    }
}