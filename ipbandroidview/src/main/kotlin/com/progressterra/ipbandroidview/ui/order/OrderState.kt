package com.progressterra.ipbandroidview.ui.order

import com.progressterra.ipbandroidapi.user.UserData
import com.progressterra.ipbandroidview.composable.component.BonusSwitchState
import com.progressterra.ipbandroidview.composable.component.DeliveryPickerState
import com.progressterra.ipbandroidview.composable.component.GoodsReceipt
import com.progressterra.ipbandroidview.composable.component.PaymentMethodState
import com.progressterra.ipbandroidview.composable.component.PaymentType
import com.progressterra.ipbandroidview.composable.component.PromoCodeState
import com.progressterra.ipbandroidview.composable.component.ReceiptState
import com.progressterra.ipbandroidview.composable.component.ReceiveReceiptState
import com.progressterra.ipbandroidview.core.ScreenState
import com.progressterra.ipbandroidview.model.Cart
import com.progressterra.ipbandroidview.model.DeliveryMethod
import com.progressterra.ipbandroidview.model.SimplePrice

data class OrderState(
    val cart: Cart = Cart(),
    override val address: String = UserData.address,
    override val entryway: String = "",
    override val apartment: String = "",
    override val comment: String = "",
    override val selectedDeliveryMethod: DeliveryMethod? = null,
    override val deliveryMethods: List<DeliveryMethod> = emptyList(),
    override val currentPaymentMethod: PaymentType? = null,
    override val promoCodeValue: SimplePrice = SimplePrice(),
    override val promoCode: String = "",
    override val deliveryPrice: SimplePrice = SimplePrice(),
    override val paidWithBonuses: SimplePrice = SimplePrice(),
    override val goodsReceipts: List<GoodsReceipt> = emptyList(),
    override val paymentReady: Boolean = false,
    override val receiveReceipt: Boolean = false,
    override val email: String = UserData.email,
    override val availableBonuses: Int = 0,
    override val useBonuses: Boolean = false,
    val screenState: ScreenState = ScreenState.LOADING
) : DeliveryPickerState,
    BonusSwitchState,
    PromoCodeState,
    PaymentMethodState,
    ReceiveReceiptState,
    ReceiptState {

    override val totalPrice: SimplePrice
        get() = cart.totalPrice
}
