package com.progressterra.ipbandroidview.ui.order

import com.progressterra.ipbandroidapi.user.UserData
import com.progressterra.ipbandroidview.components.BonusSwitchState
import com.progressterra.ipbandroidview.components.DeliveryPickerState
import com.progressterra.ipbandroidview.components.GoodsLineItem
import com.progressterra.ipbandroidview.components.GoodsLineState
import com.progressterra.ipbandroidview.components.GoodsReceipt
import com.progressterra.ipbandroidview.components.PaymentMethodState
import com.progressterra.ipbandroidview.components.PaymentType
import com.progressterra.ipbandroidview.components.PromoCodeState
import com.progressterra.ipbandroidview.components.ReceiptState
import com.progressterra.ipbandroidview.components.ReceiveReceiptState
import com.progressterra.ipbandroidview.core.ScreenState
import com.progressterra.ipbandroidview.model.DeliveryMethod
import com.progressterra.ipbandroidview.model.SimplePrice

data class OrderState(
    override val address: String = UserData.address,
    override val entryway: String = "",
    override val apartment: String = "",
    override val comment: String = "",
    override val selectedDeliveryMethod: DeliveryMethod? = null,
    override val deliveryMethods: List<DeliveryMethod> = emptyList(),
    override val goods: List<GoodsLineItem> = emptyList(),
    override val currentPaymentMethod: PaymentType? = null,
    override val promoCodeValue: SimplePrice = SimplePrice(),
    override val promoCode: String = "",
    override val totalPrice: String = "",
    override val deliveryPrice: String = "",
    override val paidWithBonuses: String = "",
    override val goodsReceipts: List<GoodsReceipt> = emptyList(),
    override val paymentReady: Boolean = false,
    override val receiveReceipt: Boolean = false,
    override val email: String = UserData.email,
    override val bonuses: Int = 0,
    override val useBonuses: Boolean = false,
    val screenState: ScreenState = ScreenState.LOADING
) : GoodsLineState,
    DeliveryPickerState,
    BonusSwitchState,
    PromoCodeState,
    PaymentMethodState,
    ReceiveReceiptState,
    ReceiptState
