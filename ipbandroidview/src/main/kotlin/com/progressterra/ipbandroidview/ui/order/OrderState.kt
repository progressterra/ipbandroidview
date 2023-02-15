package com.progressterra.ipbandroidview.ui.order

import com.progressterra.ipbandroidview.composable.BonusSwitchState
import com.progressterra.ipbandroidview.composable.DeliveryPickerState
import com.progressterra.ipbandroidview.composable.GoodsLineState
import com.progressterra.ipbandroidview.composable.PaymentMethodState
import com.progressterra.ipbandroidview.composable.ReceiptState
import com.progressterra.ipbandroidview.composable.ReceiveReceiptState
import com.progressterra.ipbandroidview.core.ScreenState
import com.progressterra.ipbandroidview.model.AddressUI
import com.progressterra.ipbandroidview.model.BonusesInfo
import com.progressterra.ipbandroidview.model.Delivery
import com.progressterra.ipbandroidview.model.DeliveryType
import com.progressterra.ipbandroidview.model.OrderGoods
import com.progressterra.ipbandroidview.model.PaymentType
import com.progressterra.ipbandroidview.model.SimplePrice

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
    override val totalPrice: SimplePrice = SimplePrice()
) : DeliveryPickerState,
    BonusSwitchState,
    PaymentMethodState,
    GoodsLineState,
    ReceiveReceiptState,
    ReceiptState {

    override val deliveryPrice: SimplePrice? = selectedDeliveryMethod?.price
}
