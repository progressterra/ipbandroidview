package com.progressterra.ipbandroidview.ui.order

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import com.progressterra.ipbandroidview.R
import com.progressterra.ipbandroidview.composable.BonusSwitch
import com.progressterra.ipbandroidview.composable.DeliveryPicker
import com.progressterra.ipbandroidview.composable.GoodsLine
import com.progressterra.ipbandroidview.composable.PaymentMethod
import com.progressterra.ipbandroidview.composable.PromoCode
import com.progressterra.ipbandroidview.composable.Receipt
import com.progressterra.ipbandroidview.composable.ReceiveReceipt
import com.progressterra.ipbandroidview.composable.ThemedLayout
import com.progressterra.ipbandroidview.composable.ThemedTopAppBar
import com.progressterra.ipbandroidview.composable.StateBox
import com.progressterra.ipbandroidview.model.delivery.Delivery
import com.progressterra.ipbandroidview.model.payment.PaymentType
import com.progressterra.ipbandroidview.theme.AppTheme

@Composable
fun OrderScreen(
    state: () -> OrderState,
    back: () -> Unit,
    goodsDetails: (String) -> Unit,
    changeAddress: () -> Unit,
    selectPickUpPoint: () -> Unit,
    selectDeliveryMethod: (Delivery) -> Unit,
    selectPayment: (PaymentType) -> Unit,
    editComment: (String) -> Unit,
    changeUseBonuses: (Boolean) -> Unit,
    editPromoCode: (String) -> Unit,
    applyPromoCode: () -> Unit,
    changeReceiveReceipt: (Boolean) -> Unit,
    editEmail: (String) -> Unit,
    payment: () -> Unit,
    openUrl: (String) -> Unit,
    refresh: () -> Unit
) {
    ThemedLayout(topBar = {
        ThemedTopAppBar(title = stringResource(R.string.order), onBack = back)
    }) { _, _ ->
        StateBox(
            state = state()::screenState, refresh = refresh
        ) {
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .verticalScroll(rememberScrollState())
                    .padding(
                        top = AppTheme.dimensions.small,
                        start = AppTheme.dimensions.small,
                        end = AppTheme.dimensions.small
                    ),
                verticalArrangement = Arrangement.spacedBy(AppTheme.dimensions.small)
            ) {
                GoodsLine(state = state, openGoodsDetails = goodsDetails)
                DeliveryPicker(
                    state = state,
                    changeAddress = changeAddress,
                    selectPickUpPoint = selectPickUpPoint,
                    selectDeliveryMethod = selectDeliveryMethod,
                    editComment = editComment
                )
                BonusSwitch(state = state, switchUseBonuses = changeUseBonuses)
                PromoCode(
                    state = state,
                    editPromoCode = editPromoCode,
                    applyPromoCode = applyPromoCode
                )
                PaymentMethod(state = state, select = selectPayment)
                ReceiveReceipt(state = state, check = changeReceiveReceipt, email = editEmail)
                Receipt(state = state, payment = payment, openUrl = openUrl)
            }
        }
    }
}