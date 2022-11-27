package com.progressterra.ipbandroidview.ui.order

import com.progressterra.ipbandroidview.data.UserData
import com.progressterra.ipbandroidview.composable.component.BonusSwitchState
import com.progressterra.ipbandroidview.composable.component.DeliveryPickerState
import com.progressterra.ipbandroidview.composable.component.GoodsReceipt
import com.progressterra.ipbandroidview.composable.component.PaymentMethodState
import com.progressterra.ipbandroidview.model.PaymentType
import com.progressterra.ipbandroidview.composable.component.PromoCodeState
import com.progressterra.ipbandroidview.composable.component.ReceiptState
import com.progressterra.ipbandroidview.composable.component.ReceiveReceiptState
import com.progressterra.ipbandroidview.core.ScreenState
import com.progressterra.ipbandroidview.model.DeliveryMethod
import com.progressterra.ipbandroidview.model.OrderGoods
import com.progressterra.ipbandroidview.model.SimplePrice

data class OrderState(
    val goods: List<OrderGoods> = emptyList(),
    override val address: String = "",
    override val entryway: String = "",
    override val apartment: String = "",
    override val comment: String = "",
    override val selectedDeliveryMethod: DeliveryMethod? = null,
    override val deliveryMethods: List<DeliveryMethod> = emptyList(),
    override val paymentMethods: List<PaymentType> = emptyList(),
    override val currentPaymentMethod: PaymentType? = null,
    override val promoCode: SimplePrice = SimplePrice(),
    override val deliveryPrice: SimplePrice = SimplePrice(),
    override val paidWithBonuses: SimplePrice = SimplePrice(),
    override val goodsReceipts: List<GoodsReceipt> = emptyList(),
    override val paymentReady: Boolean = false,
    override val receiveReceipt: Boolean = false,
    override val email: String = UserData.email,
    override val availableBonuses: Int = 0,
    override val useBonuses: Boolean = false,
    val screenState: ScreenState = ScreenState.LOADING,
    override val promoCodeName: String = "",
    override val totalPrice: SimplePrice = SimplePrice()
) : DeliveryPickerState,
    BonusSwitchState,
    PromoCodeState,
    PaymentMethodState,
    ReceiveReceiptState,
    ReceiptState
