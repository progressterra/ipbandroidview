package com.progressterra.ipbandroidview.composable

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.size
import androidx.compose.material.IconButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.progressterra.ipbandroidview.theme.AppTheme

private val size = 64.dp

@Composable
fun ThemedRefreshButton(modifier: Modifier = Modifier, onClick: () -> Unit) {
    IconButton(
        modifier = modifier
            .size(size)
            .clip(AppTheme.shapes.button)
            .background(AppTheme.colors.primary), onClick = onClick
    ) {
        RefreshIcon()
    }
}

@Preview
@Composable
private fun ThemedRefreshButtonPreview() {
    AppTheme {
        ThemedRefreshButton(onClick = {})
    }
}