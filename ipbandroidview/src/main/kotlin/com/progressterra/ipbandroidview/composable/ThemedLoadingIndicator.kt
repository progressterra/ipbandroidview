package com.progressterra.ipbandroidview.composable

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.progressterra.ipbandroidview.theme.AppTheme

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
        CircularProgressIndicator(color = AppTheme.colors.primary)
    }
}

@Preview
@Composable
private fun ThemedLoadingIndicatorPreview() {
    AppTheme {
        ThemedLoadingIndicator()
    }
}