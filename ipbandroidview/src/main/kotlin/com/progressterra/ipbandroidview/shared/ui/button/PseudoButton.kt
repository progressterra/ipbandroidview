package com.progressterra.ipbandroidview.shared.ui.button

import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.progressterra.ipbandroidview.R
import com.progressterra.ipbandroidview.shared.theme.IpbTheme
import com.progressterra.ipbandroidview.shared.ui.BrushedIcon
import com.progressterra.ipbandroidview.shared.ui.BrushedText
import com.progressterra.ipbandroidview.shared.ui.niceClickable

@Composable
fun PseudoButton(
    modifier: Modifier = Modifier,
    state: ButtonState,
    title: String,
    icId: Int,
    useComponent: UseButton
) {
    Row(modifier = modifier
        .clip(RoundedCornerShape(8.dp))
        .border(
            width = 1.dp,
            brush = IpbTheme.colors.primary.asBrush(),
            shape = RoundedCornerShape(8.dp)
        )
        .niceClickable(state.enabled) { useComponent.handle(ButtonEvent(state.id)) }
        .padding(horizontal = 8.dp, vertical = 13.dp),
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically) {
        BrushedText(
            text = title,
            style = IpbTheme.typography.body,
            tint = if (state.enabled) IpbTheme.colors.textPrimary.asBrush() else IpbTheme.colors.textDisabled.asBrush()
        )
        BrushedIcon(
            resId = icId,
            tint = IpbTheme.colors.iconPrimary3.asBrush()
        )
    }
}

@Preview
@Composable
private fun PseudoButtonPreview() {
    IpbTheme {
        Column {
            PseudoButton(
                state = ButtonState(),
                title = "Button",
                useComponent = UseButton.Empty(),
                icId = R.drawable.ic_camera
            )
            Spacer(Modifier.height(10.dp))
            PseudoButton(
                state = ButtonState(enabled = false),
                title = "Button",
                useComponent = UseButton.Empty(),
                icId = R.drawable.ic_camera
            )
        }
    }
}
