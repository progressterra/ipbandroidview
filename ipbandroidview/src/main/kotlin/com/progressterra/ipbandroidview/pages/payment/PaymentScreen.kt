package com.progressterra.ipbandroidview.pages.payment

import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.progressterra.ipbandroidview.R
import com.progressterra.ipbandroidview.entities.SimplePrice
import com.progressterra.ipbandroidview.features.bonusswitch.BonusSwitch
import com.progressterra.ipbandroidview.features.ordersteps.OrderSteps
import com.progressterra.ipbandroidview.features.ordersteps.OrderStepsState
import com.progressterra.ipbandroidview.features.paymentmethod.PaymentMethod
import com.progressterra.ipbandroidview.features.paymentmethod.PaymentMethodState
import com.progressterra.ipbandroidview.features.receipt.Receipt
import com.progressterra.ipbandroidview.features.receipt.ReceiptState
import com.progressterra.ipbandroidview.features.topbar.TopBar
import com.progressterra.ipbandroidview.shared.ScreenState
import com.progressterra.ipbandroidview.shared.ui.ThemedLayout
import com.progressterra.ipbandroidview.shared.ui.statebox.StateColumn

@Composable
fun PaymentScreen(
    state: PaymentState, useComponent: UsePayment
) {
    ThemedLayout(topBar = {
        TopBar(
            title = stringResource(R.string.processing), useComponent = useComponent
        )
    }) { _, _ ->
        StateColumn(
            scrollable = true,
            state = state.screenState,
            useComponent = useComponent
        ) {
            Spacer(Modifier.height(40.dp))
            OrderSteps(
                state = OrderStepsState.DELIVERY
            )
            Spacer(Modifier.height(8.dp))
            PaymentMethod(
                state = state.paymentMethod, useComponent = useComponent
            )
            Spacer(Modifier.height(8.dp))
            BonusSwitch(
                state = state.bonusSwitch, useComponent = useComponent
            )
            Spacer(Modifier.height(40.dp))
            Receipt(
                state = state.receipt, useComponent = useComponent
            )
        }
    }
}

@Preview
@Composable
private fun PaymentScreenPreview() {
    PaymentScreen(
        state = PaymentState(
            screenState = ScreenState.SUCCESS, receipt = ReceiptState(
                total = SimplePrice(12500), items = listOf(
                    ReceiptState.Item(
                        name = "Кофе", price = SimplePrice(10000), quantity = 1
                    ), ReceiptState.Item(
                        name = "Сахар", price = SimplePrice(2500), quantity = 2
                    )
                )
            ), paymentMethod = PaymentMethodState()
        ), useComponent = UsePayment.Empty()
    )
}