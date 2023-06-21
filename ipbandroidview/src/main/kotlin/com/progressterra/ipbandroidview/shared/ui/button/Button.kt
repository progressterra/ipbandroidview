package com.progressterra.ipbandroidview.shared.ui.button

import androidx.compose.foundation.background
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
import com.progressterra.ipbandroidview.IpbAndroidViewSettings
import com.progressterra.ipbandroidview.shared.theme.IpbTheme
import com.progressterra.ipbandroidview.shared.ui.BrushedText
import com.progressterra.ipbandroidview.shared.ui.niceClickable

@Composable
fun Button(
    modifier: Modifier = Modifier,
    state: ButtonState,
    title: String,
    useComponent: UseButton
) {
    Row(modifier = modifier
        .clip(RoundedCornerShape(IpbAndroidViewSettings.BUTTON_ROUNDING.dp))
        .background(if (state.enabled) IpbTheme.colors.primary.asBrush() else IpbTheme.colors.primaryDisabled.asBrush())
        .niceClickable(state.enabled) { useComponent.handle(ButtonEvent(state.id)) }
        .padding(horizontal = 32.dp, vertical = 15.dp),
        horizontalArrangement = Arrangement.Center,
        verticalAlignment = Alignment.CenterVertically) {
        BrushedText(
            text = title,
            style = IpbTheme.typography.headline,
            tint = if (state.enabled) IpbTheme.colors.textButton.asBrush() else IpbTheme.colors.textDisabled.asBrush()
        )
    }
}

@Preview
@Composable
private fun ButtonPreview() {
    IpbTheme {
        Column {
            Button(
                state = ButtonState(),
                title = "Button",
                useComponent = UseButton.Empty()
            )
            Spacer(Modifier.height(10.dp))
            Button(
                state = ButtonState(enabled = false),
                title = "Button",
                useComponent = UseButton.Empty()
            )
        }
    }
}
