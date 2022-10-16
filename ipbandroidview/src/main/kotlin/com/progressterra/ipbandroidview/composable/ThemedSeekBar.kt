package com.progressterra.ipbandroidview.composable

import androidx.compose.material.Slider
import androidx.compose.material.SliderDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import com.progressterra.ipbandroidview.theme.AppTheme

@Composable
fun ThemedSeekBar(
    modifier: Modifier = Modifier,
    value: Float,
    onValueChange: (Float) -> Unit
) {
    Slider(
        modifier = modifier, value = value, onValueChange = {
            if (value != it)
                onValueChange(it)
        }, colors = SliderDefaults.colors(
            thumbColor = AppTheme.colors.primary,
            disabledThumbColor = Color.Transparent,
            activeTickColor = Color.Transparent,
            inactiveTickColor = Color.Transparent,
            activeTrackColor = AppTheme.colors.primary,
            inactiveTrackColor = AppTheme.colors.surfaces,
            disabledActiveTickColor = Color.Transparent,
            disabledInactiveTickColor = Color.Transparent,
            disabledActiveTrackColor = Color.Transparent,
            disabledInactiveTrackColor = Color.Transparent
        )
    )
}

@Preview
@Composable
fun ThemedSeekBarPreview() {
    AppTheme {
        ThemedSeekBar(value = 0.3f, onValueChange = {})
    }
}