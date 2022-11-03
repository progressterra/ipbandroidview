package com.progressterra.ipbandroidview.components

import androidx.compose.material.FloatingActionButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.progressterra.ipbandroidview.theme.AppTheme

@Composable
fun LocationButton(
    modifier: Modifier = Modifier, onClick: () -> Unit
) {
    FloatingActionButton(
        modifier = modifier, onClick = onClick, backgroundColor = AppTheme.colors.primary
    ) {
        LocationIcon()
    }
}

@Preview
@Composable
private fun LocationButtonPreview() {
    AppTheme {
        LocationButton(onClick = {})
    }
}
