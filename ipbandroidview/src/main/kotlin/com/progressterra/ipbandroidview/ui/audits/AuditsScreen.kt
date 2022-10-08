package com.progressterra.ipbandroidview.ui.audits

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.progressterra.ipbandroidview.theme.AppTheme

@Composable
fun AuditsScreen() {
    Surface(
        modifier = Modifier.fillMaxSize(), color = AppTheme.colors.background
    ) {

    }
}

@Preview
@Composable
private fun AuditsScreenPreview() {
    AppTheme {
        AuditsScreen()
    }
}