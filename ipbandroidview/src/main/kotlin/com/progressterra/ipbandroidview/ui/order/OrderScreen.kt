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
import com.progressterra.ipbandroidview.composable.ReceiveReceiptComponent
import com.progressterra.ipbandroidview.composable.StateBox
import com.progressterra.ipbandroidview.composable.ThemedLayout
import com.progressterra.ipbandroidview.composable.ThemedTopAppBar
import com.progressterra.ipbandroidview.composable.component.ReceiptComponent
import com.progressterra.ipbandroidview.theme.AppTheme

@Composable
fun OrderScreen(
    state: OrderState,
    interactor: OrderInteractor
) {
//    ThemedLayout(topBar = {
//        ThemedTopAppBar(title = stringResource(R.string.order), onBack = { interactor.onBack() })
//    }) { _, _ ->
//        StateBox(
//            state = state.screenState, refresh = { interactor.refresh() }
//        ) {
//            Column(
//                modifier = Modifier
//                    .fillMaxSize()
//                    .verticalScroll(rememberScrollState())
//                    .padding(
//                        top = AppTheme.dimensions.small,
//                        start = AppTheme.dimensions.small,
//                        end = AppTheme.dimensions.small
//                    ),
//                verticalArrangement = Arrangement.spacedBy(AppTheme.dimensions.small)
//            ) {
//                GoodsLine(state = state, openGoodsDetails = { interactor.onGoodsDetails(it) })
//                DeliveryPicker(
//                    state = state,
//                    changeAddress = { interactor.changeAddress() },
//                    selectPickUpPoint = { interactor.selectPickUpPoint() },
//                    selectDeliveryMethod = { interactor.selectDeliveryMethod(it) },
//                    editComment = { interactor.editComment(it) }
//                )
//                BonusSwitch(state = state, switchUseBonuses = { interactor.changeUseBonuses(it) })
//                PaymentMethod(state = state, select = { interactor.selectPayment(it) })
//                ReceiveReceiptComponent(
//                    state = state,
//                    check = { interactor.changeReceiveReceipt(it) },
//                    email = { interactor.editEmail(it) }
//                )
//                ReceiptComponent(
//                    state = state.receiptComponentState,
//                    onEvent = { interactor.handleEvent(it) }
//                )
//            }
//        }
//    }
}