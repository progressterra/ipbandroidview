package com.progressterra.ipbandroidview.base

import androidx.compose.foundation.layout.RowScope
import androidx.compose.runtime.Composable

interface ConfigureScreen {

    fun configure(
        onBack: (() -> Unit)? = null,
        title: String? = null,
        actions: (@Composable RowScope.() -> Unit)? = null,
        bottomBarVisibility: Boolean = false
    )
}