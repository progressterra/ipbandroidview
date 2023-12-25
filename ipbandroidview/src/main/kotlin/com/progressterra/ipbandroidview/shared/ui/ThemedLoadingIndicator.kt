package com.progressterra.ipbandroidview.shared.ui

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.progressterra.ipbandroidview.shared.theme.IpbTheme

@Composable
fun ThemedLoadingIndicator(
    modifier: Modifier = Modifier, visible: Boolean = true
) {
    AnimatedVisibility(
        modifier = modifier,
        visible = visible,
        enter = fadeIn(),
        exit = fadeOut()
    ) {
        CircularProgressIndicator(color = IpbTheme.colors.primary.asColor())
    }
}

@Preview
@Composable
private fun ThemedLoadingIndicatorPreview() {
    IpbTheme {
        ThemedLoadingIndicator()
    }
}