package com.progressterra.ipbandroidview.ui.order

import com.progressterra.ipbandroidview.composable.component.BonusSwitchState
import com.progressterra.ipbandroidview.composable.component.DeliveryPickerState
import com.progressterra.ipbandroidview.composable.component.GoodsLineState
import com.progressterra.ipbandroidview.composable.component.PaymentMethodState
import com.progressterra.ipbandroidview.composable.component.PromoCodeState
import com.progressterra.ipbandroidview.composable.component.ReceiptState
import com.progressterra.ipbandroidview.composable.component.ReceiveReceiptState
import com.progressterra.ipbandroidview.core.ScreenState
import com.progressterra.ipbandroidview.model.AddressUI
import com.progressterra.ipbandroidview.model.Delivery
import com.progressterra.ipbandroidview.model.OrderGoods
import com.progressterra.ipbandroidview.model.PaymentType
import com.progressterra.ipbandroidview.model.SimplePrice

data class OrderState(
    override val goods: List<OrderGoods> = emptyList(),
    override val addressUI: AddressUI = AddressUI(),
    override val selectedDeliveryMethod: Delivery? = null,
    override val deliveryMethods: List<Delivery> = emptyList(),
    override val selectedPaymentMethod: PaymentType? = null,
    override val paymentMethods: List<PaymentType> = emptyList(),
    override val promoCode: SimplePrice = SimplePrice(),
    override val paidWithBonuses: SimplePrice = SimplePrice(),
    override val paymentReady: Boolean = false,
    override val receiveReceipt: Boolean = false,
    override val email: String = "",
    override val availableBonuses: Int = 0,
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

    override val deliveryPrice: SimplePrice = selectedDeliveryMethod?.price ?: SimplePrice()
}
