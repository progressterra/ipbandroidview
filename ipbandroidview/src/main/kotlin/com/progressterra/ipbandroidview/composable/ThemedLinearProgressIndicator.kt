package com.progressterra.ipbandroidview.composable

import androidx.compose.material.LinearProgressIndicator
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.progressterra.ipbandroidview.theme.AppTheme

@Composable
fun ThemedLinearProgressIndicator(
    modifier: Modifier = Modifier,
    progress: () -> Float
) {
    LinearProgressIndicator(
        modifier = modifier,
        color = AppTheme.colors.primary,
        backgroundColor = AppTheme.colors.surfaces,
        progress = progress()
    )
}

@Preview
@Composable
private fun ThemedLinearProgressIndicatorPreview() {
    AppTheme {
        ThemedLinearProgressIndicator(progress = { 0.4f })
    }
}