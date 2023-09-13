package com.progressterra.ipbandroidview.pages.payment

import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
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
import com.progressterra.ipbandroidview.shared.ui.ThemedLayout
import com.progressterra.ipbandroidview.shared.ui.statecolumn.ScreenState
import com.progressterra.ipbandroidview.shared.ui.statecolumn.StateColumn
import com.progressterra.ipbandroidview.shared.ui.statecolumn.StateColumnState

@Composable
fun PaymentScreen(
    modifier: Modifier = Modifier, state: PaymentScreenState, useComponent: UsePaymentScreen
) {
    ThemedLayout(
        modifier = modifier,
        topBar = {
            TopBar(
                title = stringResource(R.string.processing), useComponent = useComponent,
                showBackButton = true
            )
        }, bottomBar = {
            Receipt(
                modifier = Modifier.padding(start = 8.dp, end = 8.dp),
                state = state.receipt, useComponent = useComponent
            )
        }) { _, _ ->
        StateColumn(
            scrollable = true,
            state = state.screen,
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
        }
    }
}

@Preview
@Composable
private fun PaymentScreenPreview() {
    PaymentScreen(
        state = PaymentScreenState(
            screen = StateColumnState(state = ScreenState.SUCCESS), receipt = ReceiptState(
                total = SimplePrice(12500), items = listOf(
                    ReceiptState.Item(
                        name = "Кофе", price = SimplePrice(10000), quantity = 1
                    ), ReceiptState.Item(
                        name = "Сахар", price = SimplePrice(2500), quantity = 2
                    )
                )
            ), paymentMethod = PaymentMethodState()
        ), useComponent = UsePaymentScreen.Empty()
    )
}