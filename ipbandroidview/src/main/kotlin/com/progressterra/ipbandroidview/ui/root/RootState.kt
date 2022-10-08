package com.progressterra.ipbandroidview.ui.root

import androidx.compose.foundation.layout.RowScope
import androidx.compose.runtime.Composable

data class RootState(
    val onBack: (() -> Unit)? = null,
    val title: String? = "",
    val actions: (@Composable RowScope.() -> Unit)? = null,
    val bottomBarVisibility: Boolean = false,
)