package com.progressterra.ipbandroidview.ui.order

import com.progressterra.ipbandroidview.composable.component.BonusSwitchState
import com.progressterra.ipbandroidview.composable.component.DeliveryPickerState
import com.progressterra.ipbandroidview.composable.component.GoodsLineState
import com.progressterra.ipbandroidview.composable.component.PaymentMethodState
import com.progressterra.ipbandroidview.composable.component.PromoCodeState
import com.progressterra.ipbandroidview.composable.component.ReceiptState
import com.progressterra.ipbandroidview.composable.component.ReceiveReceiptState
import com.progressterra.ipbandroidview.core.ScreenState
import com.progressterra.ipbandroidview.model.address.AddressUI
import com.progressterra.ipbandroidview.model.bonuses.BonusesInfo
import com.progressterra.ipbandroidview.model.delivery.Delivery
import com.progressterra.ipbandroidview.model.delivery.DeliveryType
import com.progressterra.ipbandroidview.model.store.OrderGoods
import com.progressterra.ipbandroidview.model.payment.PaymentType
import com.progressterra.ipbandroidview.model.store.SimplePrice

data class OrderState(
    override val goods: List<OrderGoods> = emptyList(),
    override val addressUI: AddressUI = AddressUI(),
    override val selectedDeliveryMethod: Delivery? = null,
    override val deliveryMethods: Map<DeliveryType, Delivery> = emptyMap(),
    override val selectedPaymentMethod: PaymentType? = null,
    override val paymentMethods: List<PaymentType> = emptyList(),
    override val promoCode: SimplePrice? = null,
    override val paymentReady: Boolean = false,
    override val receiveReceipt: Boolean = false,
    override val email: String = "",
    override val availableBonuses: BonusesInfo = BonusesInfo(),
    override val useBonuses: Boolean = false,
    val screenState: ScreenState = ScreenState.LOADING,
    override val promoCodeName: String = "",
    override val totalPrice: SimplePrice = SimplePrice()
) : DeliveryPickerState,
    BonusSwitchState,
    PromoCodeState,
    PaymentMethodState,
    GoodsLineState,
    ReceiveReceiptState,
    ReceiptState {

    override val deliveryPrice: SimplePrice? = selectedDeliveryMethod?.price
}
