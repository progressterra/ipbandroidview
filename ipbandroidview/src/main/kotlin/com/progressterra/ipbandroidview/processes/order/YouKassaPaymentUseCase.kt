package com.progressterra.ipbandroidview.processes.order

import android.app.Activity.RESULT_CANCELED
import android.app.Activity.RESULT_OK
import android.content.Context
import androidx.compose.ui.graphics.toArgb
import com.progressterra.ipbandroidapi.api.cart.CartService
import com.progressterra.ipbandroidapi.api.cart.models.StatusResult
import com.progressterra.ipbandroidapi.api.product.ProductRepository
import com.progressterra.ipbandroidview.entities.SimplePrice
import com.progressterra.ipbandroidview.entities.toGoodsItem
import com.progressterra.ipbandroidview.entities.toReceiptItems
import com.progressterra.ipbandroidview.features.ordernumber.OrderNumberState
import com.progressterra.ipbandroidview.pages.orderstatus.OrderStatusScreenState
import com.progressterra.ipbandroidview.processes.ToastedException
import com.progressterra.ipbandroidview.processes.utils.MakeToastUseCase
import com.progressterra.ipbandroidview.processes.utils.ManageResources
import com.progressterra.ipbandroidview.processes.utils.ObtainAccessToken
import com.progressterra.ipbandroidview.processes.utils.StartActivityForResultContract
import com.progressterra.ipbandroidview.shared.IpbAndroidViewSettings
import com.progressterra.ipbandroidview.shared.mvi.AbstractTokenUseCase
import ru.yoomoney.sdk.kassa.payments.Checkout
import ru.yoomoney.sdk.kassa.payments.TokenizationResult
import ru.yoomoney.sdk.kassa.payments.checkoutParameters.Amount
import ru.yoomoney.sdk.kassa.payments.checkoutParameters.PaymentParameters
import ru.yoomoney.sdk.kassa.payments.checkoutParameters.SavePaymentMethod
import ru.yoomoney.sdk.kassa.payments.checkoutParameters.UiParameters
import ru.yoomoney.sdk.kassa.payments.ui.color.ColorScheme
import java.math.BigDecimal
import java.util.Currency


interface YouKassaPaymentUseCase {

    suspend operator fun invoke(): Result<OrderStatusScreenState>

    class Base(
        private val context: Context,
        private val startActivityForResultContract: StartActivityForResultContract.Client,
        private val cartService: CartService,
        private val productRepository: ProductRepository,
        obtainAccessToken: ObtainAccessToken,
        makeToastUseCase: MakeToastUseCase,
        manageResources: ManageResources
    ) : YouKassaPaymentUseCase,
        AbstractTokenUseCase(obtainAccessToken, makeToastUseCase, manageResources) {

        override suspend fun invoke(): Result<OrderStatusScreenState> = withToken { token ->
            val cartResult = cartService.confirmOrder(token).also {
                if (it.result?.status != StatusResult.SUCCESS) throw ToastedException(
                    it.result?.message ?: ""
                )
            }.data
            var total = SimplePrice()
            cartResult?.listDRSale?.map { it.toReceiptItems() }?.forEach { total += it.price }
            val images = cartResult?.listDRSale?.mapNotNull {
                productRepository.productByNomenclatureId(
                    token,
                    it.idrfNomenclature!!
                ).getOrThrow()?.toGoodsItem()?.image
            } ?: emptyList()
            val intent = Checkout.createTokenizeIntent(
                context = context,
                uiParameters = UiParameters(
                    colorScheme = ColorScheme(
                        IpbAndroidViewSettings.COLORS.primary.asColor().toArgb()
                    )
                ),
                paymentParameters = PaymentParameters(
                    amount = Amount(
                        value = total.toDouble().toBigDecimal(),
                        currency = Currency.getInstance("RUB")
                    ),
                    title = "Оплата заказа ${cartResult?.number}",
                    subtitle = "",
                    clientApplicationKey = IpbAndroidViewSettings.YOU_KASSA_CLIENT_APPLICATION_KEY,
                    shopId = IpbAndroidViewSettings.YOU_KASSA_SHOP_ID,
                    savePaymentMethod = SavePaymentMethod.USER_SELECTS
                )
            )
            val result =
                startActivityForResultContract.startForResult(intent, REQUEST_CODE_TOKENIZE)
            var data: TokenizationResult? = null
            if (result.first == REQUEST_CODE_TOKENIZE) {
                when (result.second) {
                    RESULT_OK -> data = result.third?.let { Checkout.createTokenizationResult(it) }
                    RESULT_CANCELED -> Unit
                }
            }
            OrderStatusScreenState(
                id = cartResult?.idUnique!!,
                number = OrderNumberState(
                    number = cartResult.number ?: "",
                    success = data != null,
                    quantity = images.size,
                    address = cartResult.adressString ?: ""
                )
            )
        }

        companion object {

            const val REQUEST_CODE_TOKENIZE = 1
        }
    }
}