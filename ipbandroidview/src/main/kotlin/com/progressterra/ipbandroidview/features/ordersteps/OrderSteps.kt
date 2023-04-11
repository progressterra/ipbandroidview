package com.progressterra.ipbandroidview.features.ordersteps

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.height
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.progressterra.ipbandroidview.R
import com.progressterra.ipbandroidview.shared.theme.IpbTheme
import com.progressterra.ipbandroidview.shared.ui.BrushedIcon

@Composable
fun OrderSteps(
    modifier: Modifier = Modifier, state: OrderStepsState
) {
    Row(
        modifier = modifier, verticalAlignment = Alignment.CenterVertically
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
}