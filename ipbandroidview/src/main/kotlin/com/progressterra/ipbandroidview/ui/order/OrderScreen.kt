package com.progressterra.ipbandroidview.ui.order

import androidx.compose.runtime.Composable

@Composable
fun OrderScreen(
    state: OrderState,
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