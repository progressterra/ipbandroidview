package com.progressterra.ipbandroidview.features.receivereceipt

import androidx.compose.animation.animateContentSize
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
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
import com.progressterra.ipbandroidview.shared.ui.brushedswitch.BrushedSwitch
import com.progressterra.ipbandroidview.shared.ui.textfield.TextField

@Composable
fun ReceiveReceiptComponent(
    modifier: Modifier = Modifier, state: ReceiveReceiptState, useComponent: UseReceiveReceipt
) {
    Column(
        modifier = modifier
            .padding(horizontal = 20.dp)
            .clip(RoundedCornerShape(12.dp))
            .background(IpbTheme.colors.surface.asBrush())
            .animateContentSize()
            .padding(12.dp),
        verticalArrangement = Arrangement.spacedBy(8.dp)
    ) {
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            BrushedText(
                text = stringResource(R.string.receive_check),
                style = IpbTheme.typography.title,
                tint = IpbTheme.colors.textPrimary.asBrush()
            )
            BrushedSwitch(
                state = state.receiveReceipt, useComponent = useComponent
            )
        }
        if (state.receiveReceipt.turned) TextField(
            modifier = Modifier.fillMaxWidth(),
            state = state.emailState,
            useComponent = useComponent
        )
    }
}