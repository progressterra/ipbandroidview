package com.progressterra.ipbandroidview.core

import androidx.compose.foundation.layout.RowScope
import androidx.compose.runtime.Composable

data class ScreenConfiguration(
    val onBack: (() -> Unit)? = null,
    val title: String? = null,
    val actions: @Composable (RowScope.() -> Unit)? = null,
    val topBarVisibility: Boolean = false,
    val bottomBarVisibility: Boolean = false
)
