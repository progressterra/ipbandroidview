package com.progressterra.ipbandroidview.composable

import androidx.compose.material.LinearProgressIndicator
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.progressterra.ipbandroidview.theme.IpbTheme

@Composable
fun ThemedLinearProgressIndicator(
    modifier: Modifier = Modifier,
    progress: Float
) {
    LinearProgressIndicator(
        modifier = modifier,
        color = IpbTheme.colors.primary,
        backgroundColor = IpbTheme.colors.surfaces,
        progress = progress
    )
}

@Preview
@Composable
private fun ThemedLinearProgressIndicatorPreview() {
    IpbTheme {
        ThemedLinearProgressIndicator(progress = 0.4f)
    }
}