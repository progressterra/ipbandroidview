package com.progressterra.ipbandroidview.features.countdown

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
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
import com.progressterra.ipbandroidview.shared.ui.niceClickable

@Composable
fun CountDown(
    modifier: Modifier = Modifier, state: CountDownState, useComponent: UseCountDown
) {
    Row(
        modifier = modifier
            .clip(RoundedCornerShape(14.dp))
            .padding(horizontal = 32.dp, vertical = 15.dp)
            .niceClickable(enabled = state.enabled) { useComponent.handle(CountdownEvent) },
        horizontalArrangement = Arrangement.Center,
        verticalAlignment = Alignment.CenterVertically
    ) {
        BrushedText(
            text = if (state.enabled) stringResource(R.string.resend) else state.count,
            style = IpbTheme.typography.headline,
            tint = IpbTheme.colors.textDisabled.asBrush()
        )
    }
}