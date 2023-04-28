package com.progressterra.ipbandroidview.shared.theme

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.ColumnScope
import androidx.compose.runtime.Composable
import androidx.compose.ui.unit.dp

@Composable
fun Preview(content: @Composable ColumnScope.() -> Unit) {
    IpbTheme {
        Column(content = content, verticalArrangement = Arrangement.spacedBy(20.dp))
    }
}