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
import com.progressterra.ipbandroidview.components.BonusSwitch
import com.progressterra.ipbandroidview.components.DeliveryPicker
import com.progressterra.ipbandroidview.components.GoodsLine
import com.progressterra.ipbandroidview.components.PaymentMethod
import com.progressterra.ipbandroidview.components.PaymentType
import com.progressterra.ipbandroidview.components.PromoCode
import com.progressterra.ipbandroidview.components.Receipt
import com.progressterra.ipbandroidview.components.ReceiveReceipt
import com.progressterra.ipbandroidview.components.StateBox
import com.progressterra.ipbandroidview.components.ThemedLayout
import com.progressterra.ipbandroidview.components.bar.ThemedTopAppBar
import com.progressterra.ipbandroidview.model.DeliveryMethod
import com.progressterra.ipbandroidview.theme.AppTheme

@Composable
fun OrderScreen(
    state: () -> OrderState,
    back: () -> Unit,
    goodsDetails: (String) -> Unit,
    changeAddress: () -> Unit,
    selectPickUpPoint: () -> Unit,
    selectDeliveryMethod: (DeliveryMethod) -> Unit,
    selectPayment: (PaymentType) -> Unit,
    editApartment: (String) -> Unit,
    editComment: (String) -> Unit,
    editEntryway: (String) -> Unit,
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
            state = state()::screenState, onRefresh = refresh
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
                GoodsLine(state = state, goodsDetails = goodsDetails)
                DeliveryPicker(
                    state = state,
                    changeAddress = changeAddress,
                    selectPickUpPoint = selectPickUpPoint,
                    selectDeliveryMethod = selectDeliveryMethod,
                    editApartment = editApartment,
                    editComment = editComment,
                    editEntryway = editEntryway
                )
                BonusSwitch(state = state, onChange = changeUseBonuses)
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