package com.progressterra.ipbandroidview.widgets.cartsummary

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.progressterra.ipbandroidview.R
import com.progressterra.ipbandroidview.shared.theme.IpbTheme
import com.progressterra.ipbandroidview.shared.theme.Preview
import com.progressterra.ipbandroidview.shared.ui.BrushedText
import com.progressterra.ipbandroidview.shared.ui.button.Button

@Composable
fun CartSummary(
    modifier: Modifier = Modifier,
    state: CartSummaryState,
    useComponent: UseCartSummary
) {
    Column(
        modifier = modifier,
        verticalArrangement = Arrangement.spacedBy(10.dp)
    ) {
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            BrushedText(
                text = stringResource(R.string.total_to_pay),
                style = IpbTheme.typography.title,
                tint = IpbTheme.colors.textPrimary.asBrush(),
            )
            BrushedText(
                text = state.total.toString(),
                style = IpbTheme.typography.title,
                tint = IpbTheme.colors.textPrimary.asBrush(),
            )
        }
        Button(
            modifier = Modifier.fillMaxWidth(),
            state = state.proceed,
            title = stringResource(R.string.order_proceed),
            useComponent = useComponent
        )
    }
}

@Preview
@Composable
private fun CartSummaryPreview() {
    Preview {
        CartSummary(
            state = CartSummaryState(),
            useComponent = UseCartSummary.Empty()
        )
    }
}