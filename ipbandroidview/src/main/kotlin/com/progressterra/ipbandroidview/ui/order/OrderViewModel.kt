package com.progressterra.ipbandroidview.ui.order

import androidx.lifecycle.ViewModel
import com.progressterra.ipbandroidview.core.ScreenState
import com.progressterra.ipbandroidview.domain.usecase.bonus.UseBonusesUseCase
import com.progressterra.ipbandroidview.domain.usecase.delivery.AvailableDeliveryUseCase
import com.progressterra.ipbandroidview.domain.usecase.delivery.PaymentMethodsUseCase
import com.progressterra.ipbandroidview.domain.usecase.delivery.SetDeliveryAddressUseCase
import com.progressterra.ipbandroidview.domain.usecase.order.ConfirmOrderUseCase
import com.progressterra.ipbandroidview.domain.usecase.user.FetchUserAddressUseCase
import com.progressterra.ipbandroidview.domain.usecase.user.FetchUserEmailUseCase
import com.progressterra.ipbandroidview.model.Delivery
import com.progressterra.ipbandroidview.model.OrderGoods
import com.progressterra.ipbandroidview.model.PaymentType
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
    private val fetchUserEmailUseCase: FetchUserEmailUseCase,
    private val paymentMethodsUseCase: PaymentMethodsUseCase,
    private val setDeliveryAddressUseCase: SetDeliveryAddressUseCase
) : ViewModel(), ContainerHost<OrderState, OrderEffect> {

    override val container: Container<OrderState, OrderEffect> = container(OrderState())

    fun refresh() = intent {
        reduce { state.copy(screenState = ScreenState.LOADING) }
        fetchUserEmailUseCase.fetch().onSuccess { email ->
            reduce { state.copy(email = email) }
            availableDeliveryUseCase.deliveries().onSuccess { deliveryMethods ->
                reduce { state.copy(deliveryMethods = deliveryMethods) }
                fetchUserAddressUseCase.fetch().onSuccess { address ->
                    reduce { state.copy(addressUI = address) }
                    paymentMethodsUseCase.methods().onSuccess { paymentMethods ->
                        reduce {
                            state.copy(
                                paymentMethods = paymentMethods,
                                screenState = ScreenState.SUCCESS
                            )
                        }
                    }.onFailure { reduce { state.copy(screenState = ScreenState.ERROR) } }
                }.onFailure { reduce { state.copy(screenState = ScreenState.ERROR) } }
            }.onFailure { reduce { state.copy(screenState = ScreenState.ERROR) } }
        }.onFailure { reduce { state.copy(screenState = ScreenState.ERROR) } }
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

    fun selectDeliveryMethod(method: Delivery) = intent {
        reduce { state.copy(selectedDeliveryMethod = method) }
        checkValidity()
    }

    fun selectPayment(payment: PaymentType) = intent {
        reduce { state.copy(selectedPaymentMethod = payment) }
        checkValidity()
    }

    fun editComment(comment: String) = intent {
        if (state.selectedDeliveryMethod is Delivery.CourierDelivery) {
            reduce {
                state.copy(
                    selectedDeliveryMethod = (state.selectedDeliveryMethod as Delivery.CourierDelivery).copy(
                        commentary = comment
                    )
                )
            }
        }
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
        state.selectedDeliveryMethod?.let { deliveryMethod ->
            setDeliveryAddressUseCase.setAddress(deliveryMethod, state.addressUI).onSuccess {
                confirmOrderUseCase.confirm().onSuccess {
                    postSideEffect(OrderEffect.Next(it))
                }
            }
        }
    }

    fun openUrl(url: String) = intent { }

    private fun checkValidity() = intent {
        reduce { state.copy(paymentReady = state.selectedPaymentMethod != null && state.selectedDeliveryMethod != null) }
    }
}