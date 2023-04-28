package com.progressterra.ipbandroidview.features.ordersteps

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.progressterra.ipbandroidview.R
import com.progressterra.ipbandroidview.shared.theme.IpbTheme
import com.progressterra.ipbandroidview.shared.ui.BrushedIcon
import com.progressterra.ipbandroidview.shared.ui.BrushedText

@Composable
fun OrderSteps(
    modifier: Modifier = Modifier, state: OrderStepsState
) {
    Column(
        modifier = modifier, verticalArrangement = Arrangement.spacedBy(18.dp)
    ) {
        Row(
            verticalAlignment = Alignment.CenterVertically
        ) {
            Box(
                modifier = Modifier
                    .weight(1f)
                    .height(2.dp)
                    .background(IpbTheme.colors.primary.asBrush())
            )
            BrushedIcon(
                resId = R.drawable.ic_tick,
                tint = IpbTheme.colors.primary.asBrush(),
            )
            Box(
                modifier = Modifier
                    .weight(1f)
                    .height(2.dp)
                    .background(IpbTheme.colors.primary.asBrush())
            )
            if (state > OrderStepsState.DELIVERY) {
                BrushedIcon(
                    resId = R.drawable.ic_tick,
                    tint = IpbTheme.colors.primary.asBrush(),
                )
            } else {
                BrushedIcon(
                    resId = R.drawable.ic_untick,
                    tint = IpbTheme.colors.iconPrimary.asBrush(),
                )
            }
            if (state == OrderStepsState.DELIVERY) {
                Box(
                    modifier = Modifier
                        .weight(1f)
                        .height(2.dp)
                        .background(IpbTheme.colors.textPrimary.asBrush())
                )
            } else {
                Box(
                    modifier = Modifier
                        .weight(1f)
                        .height(2.dp)
                        .background(IpbTheme.colors.primary.asBrush())
                )
            }
            when (state) {
                OrderStepsState.DELIVERY -> BrushedIcon(
                    resId = R.drawable.ic_untick,
                    tint = IpbTheme.colors.textDisabled.asBrush(),
                )

                OrderStepsState.PAYMENT -> BrushedIcon(
                    resId = R.drawable.ic_untick,
                    tint = IpbTheme.colors.iconPrimary.asBrush(),
                )

                OrderStepsState.FINISHED -> BrushedIcon(
                    resId = R.drawable.ic_tick,
                    tint = IpbTheme.colors.primary.asBrush(),
                )
            }
            if (state == OrderStepsState.FINISHED) {
                Box(
                    modifier = Modifier
                        .weight(1f)
                        .height(2.dp)
                        .background(IpbTheme.colors.primary.asBrush())
                )
            } else {
                Box(
                    modifier = Modifier
                        .weight(1f)
                        .height(2.dp)
                        .background(IpbTheme.colors.textPrimary.asBrush())
                )
            }
        }
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceAround
        ) {
            BrushedText(
                text = stringResource(R.string.details),
                style = IpbTheme.typography.body,
                tint = IpbTheme.colors.primary.asBrush()
            )
            if (state > OrderStepsState.DELIVERY) {
                BrushedText(
                    text = stringResource(R.string.delivery),
                    style = IpbTheme.typography.body,
                    tint = IpbTheme.colors.primary.asBrush()
                )
            } else {
                BrushedText(
                    text = stringResource(R.string.delivery),
                    style = IpbTheme.typography.headline,
                    tint = IpbTheme.colors.textPrimary.asBrush()
                )
            }
            when (state) {
                OrderStepsState.DELIVERY -> BrushedText(
                    text = stringResource(R.string.payment),
                    style = IpbTheme.typography.body,
                    tint = IpbTheme.colors.textDisabled.asBrush()
                )

                OrderStepsState.PAYMENT -> BrushedText(
                    text = stringResource(R.string.payment),
                    style = IpbTheme.typography.headline,
                    tint = IpbTheme.colors.textPrimary.asBrush()
                )

                OrderStepsState.FINISHED -> BrushedText(
                    text = stringResource(R.string.payment),
                    style = IpbTheme.typography.body,
                    tint = IpbTheme.colors.primary.asBrush()
                )
            }
        }
    }
}

@Preview
@Composable
private fun OrderStepsPreview() {
    IpbTheme {
        Column {
        OrderSteps(state = OrderStepsState.DELIVERY)
        OrderSteps(state = OrderStepsState.PAYMENT)
        OrderSteps(state = OrderStepsState.FINISHED)
        }
    }
}