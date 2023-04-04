package com.progressterra.ipbandroidview.pages.payment

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.progressterra.ipbandroidview.R
import com.progressterra.ipbandroidview.features.bonusswitch.BonusSwitch
import com.progressterra.ipbandroidview.features.ordersteps.OrderSteps
import com.progressterra.ipbandroidview.features.ordersteps.OrderStepsState
import com.progressterra.ipbandroidview.features.paymentmethod.PaymentMethod
import com.progressterra.ipbandroidview.features.proshkatopbar.ProshkaTopBar
import com.progressterra.ipbandroidview.features.receipt.Receipt
import com.progressterra.ipbandroidview.features.receivereceipt.ReceiveReceiptComponent
import com.progressterra.ipbandroidview.shared.ui.ThemedLayout
import com.progressterra.ipbandroidview.shared.ui.statebox.StateBox

@Composable
fun PaymentScreen(
    state: PaymentState,
    useComponent: UsePayment
) {
    ThemedLayout(
        topBar = {
            ProshkaTopBar(
                title = stringResource(R.string.processing),
                useComponent = useComponent
            )
        },
        bottomBar = {
            Receipt(
                state = state.receipt,
                useComponent = useComponent
            )
        }
    ) { _, _ ->
        StateBox(
            state = state.stateBox, useComponent = useComponent
        ) {
            Column(
            ) {
                OrderSteps(
                    state = OrderStepsState.DELIVERY
                )
                Spacer(Modifier.height(40.dp))
                PaymentMethod(
                    state = state.paymentMethod,
                    useComponent = useComponent
                )
                Spacer(Modifier.height(8.dp))
                BonusSwitch(
                    state = state.bonusSwitch,
                    useComponent = useComponent
                )
                Spacer(Modifier.height(8.dp))
                ReceiveReceiptComponent(
                    state = state.receiveReceipt,
                    useComponent = useComponent
                )
            }
        }
    }
}