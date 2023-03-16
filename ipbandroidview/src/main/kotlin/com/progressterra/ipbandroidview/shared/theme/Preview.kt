package com.progressterra.ipbandroidview.shared.theme

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.ColumnScope
import androidx.compose.runtime.Composable

@Composable
fun Preview(content: @Composable ColumnScope.() -> Unit) {
    IpbTheme {
        Column(content = content)
    }
}