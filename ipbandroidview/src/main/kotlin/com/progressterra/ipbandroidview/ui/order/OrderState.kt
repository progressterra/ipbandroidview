package com.progressterra.ipbandroidview.ui.order

import com.progressterra.ipbandroidview.model.OrderGoods

data class OrderState(
    val goods: List<OrderGoods> = emptyList(),
//    override val addressUI: AddressUI = AddressUI(),
//    override val selectedDeliveryMethod: Delivery? = null,
//    override val deliveryMethods: Map<DeliveryType, Delivery> = emptyMap(),
//    override val selectedPaymentMethod: PaymentType? = null,
//    override val paymentMethods: List<PaymentType> = emptyList(),
//    override val receiveReceipt: Boolean = false,
//    override val email: String = "",
//    override val availableBonuses: BonusesState = BonusesState(),
//    override val useBonuses: Boolean = false,
//    val screenState: ScreenState = ScreenState.LOADING,
//    val receiptComponentState: ReceiptComponentState = ReceiptComponentState()
)
