package com.progressterra.ipbandroidview.shared.ui.button

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.SolidColor
import androidx.compose.ui.unit.dp
import com.progressterra.ipbandroidview.shared.theme.IpbTheme
import com.progressterra.ipbandroidview.shared.ui.BrushedText
import com.progressterra.ipbandroidview.shared.ui.niceClickable

@Composable
fun Button(
    modifier: Modifier = Modifier,
    state: ButtonState,
    useComponent: UseButton,
    style: ButtonStyle = ButtonStyle.DEFAULT
) {
    val backgroundColor = when (style) {
        ButtonStyle.DEFAULT -> IpbTheme.colors.primary.asBrush()
        ButtonStyle.SILENT -> IpbTheme.colors.surface1.asBrush()
        ButtonStyle.TEXT -> SolidColor(Color.Transparent)
    }
    val textColor = when (style) {
        ButtonStyle.DEFAULT -> IpbTheme.colors.textButton.asBrush()
        ButtonStyle.SILENT -> IpbTheme.colors.textDisabled.asBrush()
        ButtonStyle.TEXT -> IpbTheme.colors.textDisabled.asBrush()
    }
    Row(
        modifier = modifier
            .clip(RoundedCornerShape(14.dp))
            .background(backgroundColor)
            .padding(horizontal = 32.dp, vertical = 15.dp)
            .niceClickable { useComponent.handleEvent(state.id, ButtonEvent.Click) },
        horizontalArrangement = Arrangement.Center,
        verticalAlignment = Alignment.CenterVertically
    ) {
        BrushedText(
            text = state.text,
            style = IpbTheme.typography.button,
            tint = textColor
        )
    }
}