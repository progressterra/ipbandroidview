package com.progressterra.ipbandroidview.shared.ui.button

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.progressterra.ipbandroidview.shared.theme.IpbTheme
import com.progressterra.ipbandroidview.shared.theme.toBrush
import com.progressterra.ipbandroidview.shared.ui.BrushedText
import com.progressterra.ipbandroidview.shared.ui.niceClickable

@Composable
fun Button(
    modifier: Modifier = Modifier,
    state: ButtonState,
    title: String,
    useComponent: UseButton,
    style: ButtonStyle = ButtonStyle.DEFAULT
) {
    val backgroundColor = when (style) {
        ButtonStyle.DEFAULT -> IpbTheme.colors.primary.asBrush()
        ButtonStyle.SILENT -> IpbTheme.colors.surface.asBrush()
        ButtonStyle.TEXT -> Color.Transparent.toBrush()
        ButtonStyle.OUTLINE -> Color.Transparent.toBrush()
    }
    val textColor = when (style) {
        ButtonStyle.DEFAULT -> IpbTheme.colors.textButton.asBrush()
        ButtonStyle.SILENT -> IpbTheme.colors.textDisabled.asBrush()
        ButtonStyle.TEXT -> IpbTheme.colors.textDisabled.asBrush()
        ButtonStyle.OUTLINE -> IpbTheme.colors.textPrimary3.asBrush()
    }
    Row(modifier = modifier
        .clip(RoundedCornerShape(14.dp))
        .border(
            width = 2.dp,
            brush = IpbTheme.colors.textPrimary3.asBrush(),
            shape = RoundedCornerShape(14.dp)
        )
        .background(if (state.enabled) backgroundColor else IpbTheme.colors.primaryDisabled.asBrush())
        .niceClickable(state.enabled) { useComponent.handle(ButtonEvent.Click(state.id)) }
        .padding(horizontal = 32.dp, vertical = 15.dp),
        horizontalArrangement = Arrangement.Center,
        verticalAlignment = Alignment.CenterVertically) {
        BrushedText(
            text = title,
            style = IpbTheme.typography.headline,
            tint = if (state.enabled) textColor else IpbTheme.colors.textDisabled.asBrush()
        )
    }
}