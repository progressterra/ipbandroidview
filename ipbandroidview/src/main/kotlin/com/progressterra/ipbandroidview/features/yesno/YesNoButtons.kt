package com.progressterra.ipbandroidview.features.yesno

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.progressterra.ipbandroidview.R
import com.progressterra.ipbandroidview.shared.theme.IpbTheme
import com.progressterra.ipbandroidview.shared.ui.BrushedText
import com.progressterra.ipbandroidview.shared.ui.modifier.niceClickable

@Composable
fun YesNoButton(
    modifier: Modifier = Modifier,
    state: Boolean?,
    onClick: (Boolean) -> Unit,
    enabled: Boolean
) {

    @Composable
    fun Button(
        modifier: Modifier,
        text: String,
        role: Boolean,
        activeColor: Brush,
        alternateColor: Brush
    ) {
        val background = when (state) {
            null -> IpbTheme.colors.onSurface.asBrush()
            else -> alternateColor
        }
        Row(
            modifier = modifier
                .clip(RoundedCornerShape(14.dp))
                .background(if (state == role) activeColor else background)
                .niceClickable(enabled) { onClick(role) }
                .padding(
                    horizontal = 32.dp,
                    vertical = 16.dp
                ),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.Center
        ) {
            BrushedText(
                text = text,
                style = IpbTheme.typography.headline,
                tint = if (state == role) IpbTheme.colors.textButton.asBrush() else IpbTheme.colors.textDisabled.asBrush()
            )
        }
    }


    Row(
        modifier = modifier,
        horizontalArrangement = Arrangement.spacedBy(12.dp)
    ) {
        Button(
            modifier = Modifier.weight(1f),
            text = stringResource(id = R.string.yes),
            role = true,
            activeColor = IpbTheme.colors.primary.asBrush(),
            alternateColor = IpbTheme.colors.success.asBrush()
        )
        Button(
            modifier = Modifier.weight(1f),
            text = stringResource(id = R.string.no),
            role = false,
            activeColor = IpbTheme.colors.error.asBrush(),
            alternateColor = IpbTheme.colors.secondary.asBrush()
        )
    }
}

@Preview(showBackground = true)
@Composable
private fun YesNoButtonPreview() {
    IpbTheme {
        YesNoButton(state = null, onClick = {}, enabled = true)
    }
}

@Preview(showBackground = true)
@Composable
private fun YesNoButtonPreviewYes() {
    IpbTheme {
        YesNoButton(state = true, onClick = {}, enabled = true)
    }
}

@Preview(showBackground = true)
@Composable
private fun YesNoButtonPreviewNo() {
    IpbTheme {
        YesNoButton(
            modifier = Modifier.fillMaxWidth(),
            state = false,
            onClick = {}, enabled = true
        )
    }
}

@Preview(showBackground = true)
@Composable
private fun YesNoButtonPreviewDisabled() {
    IpbTheme {
        YesNoButton(
            modifier = Modifier.fillMaxWidth(),
            state = false,
            onClick = {},
            enabled = false
        )
    }
}