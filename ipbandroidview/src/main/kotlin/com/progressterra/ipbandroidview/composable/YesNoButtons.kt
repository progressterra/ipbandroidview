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
import com.progressterra.ipbandroidview.composable.utils.niceClickable
import com.progressterra.ipbandroidview.theme.AppTheme

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
            null -> AppTheme.colors.gray3
            else -> alternateColor
        }
        Row(
            modifier = modifier
                .clip(AppTheme.shapes.button)
                .background(if (state == role) activeColor else background)
                .niceClickable(enabled) { onClick(role) }
                .padding(
                    horizontal = AppTheme.dimensions.buttonHorizontalPadding,
                    vertical = AppTheme.dimensions.buttonVerticalPadding
                ),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.Center
        ) {
            Text(
                text = text,
                style = AppTheme.typography.button,
                color = if (state == role) AppTheme.colors.surfaces else AppTheme.colors.gray2
            )
        }
    }
    Row(
        modifier = modifier,
        horizontalArrangement = Arrangement.spacedBy(AppTheme.dimensions.medium)
    ) {
        Button(
            modifier = Modifier.weight(1f),
            text = stringResource(id = R.string.yes),
            role = true,
            activeColor = AppTheme.colors.primary,
            alternateColor = AppTheme.colors.success
        )
        Button(
            modifier = Modifier.weight(1f),
            text = stringResource(id = R.string.no),
            role = false,
            activeColor = AppTheme.colors.error,
            alternateColor = AppTheme.colors.failed
        )
    }
}

@Preview(showBackground = true)
@Composable
private fun YesNoButtonPreview() {
    AppTheme {
        YesNoButton(state = null, onClick = {}, enabled = true)
    }
}

@Preview(showBackground = true)
@Composable
private fun YesNoButtonPreviewYes() {
    AppTheme {
        YesNoButton(state = true, onClick = {}, enabled = true)
    }
}

@Preview(showBackground = true)
@Composable
private fun YesNoButtonPreviewNo() {
    AppTheme {
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
    AppTheme {
        YesNoButton(
            modifier = Modifier.fillMaxWidth(),
            state = false,
            onClick = {},
            enabled = false
        )
    }
}