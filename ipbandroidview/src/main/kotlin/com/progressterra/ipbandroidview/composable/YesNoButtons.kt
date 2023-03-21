package com.progressterra.ipbandroidview.composable

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import com.progressterra.ipbandroidview.R
import com.progressterra.ipbandroidview.shared.ui.niceClickable
import com.progressterra.ipbandroidview.shared.theme.IpbTheme

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
        activeColor: Color,
        alternateColor: Color
    ) {
        val background = when (state) {
            null -> IpbTheme.colors.gray3
            else -> alternateColor
        }
        Row(
            modifier = modifier
                .clip(IpbTheme.shapes.button)
                .background(if (state == role) activeColor else background)
                .niceClickable(enabled) { onClick(role) }
                .padding(
                    horizontal = 32.dp,
                    vertical = 15.dp
                ),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.Center
        ) {
            Text(
                text = text,
                style = IpbTheme.typography.button,
                color = if (state == role) IpbTheme.colors.surfaces else IpbTheme.colors.gray2
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
            activeColor = IpbTheme.colors.primary,
            alternateColor = IpbTheme.colors.success
        )
        Button(
            modifier = Modifier.weight(1f),
            text = stringResource(id = R.string.no),
            role = false,
            activeColor = IpbTheme.colors.error,
            alternateColor = IpbTheme.colors.failed
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