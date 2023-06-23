package com.progressterra.ipbandroidview.features.orderid

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.progressterra.ipbandroidview.R
import com.progressterra.ipbandroidview.shared.theme.IpbTheme
import com.progressterra.ipbandroidview.shared.ui.BrushedText

@Composable
fun OrderId(
    modifier: Modifier = Modifier, state: OrderIdState
) {
    Column(
        modifier = modifier
            .padding(horizontal = 20.dp)
            .fillMaxWidth()
            .clip(RoundedCornerShape(12.dp))
            .background(IpbTheme.colors.surface.asBrush())
            .padding(horizontal = 50.dp, vertical = 80.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        BrushedText(
            modifier = Modifier,
            text = if (state.success) stringResource(R.string.success_payment) else stringResource(R.string.payment_error),
            style = IpbTheme.typography.title,
            tint = IpbTheme.colors.onBackground.asBrush()
        )
        Spacer(Modifier.height(8.dp))
        BrushedText(
            modifier = Modifier,
            text = "${stringResource(R.string.order_id)} ${state.id}",
            style = IpbTheme.typography.subHeadlineRegular,
            tint = IpbTheme.colors.textTertiary.asBrush()
        )
    }
}