package com.progressterra.ipbandroidview.components.topbar

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.RowScope
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.progressterra.ipbandroidview.theme.AppTheme

@Composable
fun BasicTopAppBar(
    modifier: Modifier = Modifier,
    leftActions: (@Composable RowScope.() -> Unit)? = null,
    title: (@Composable RowScope.() -> Unit)? = null,
    rightActions: (@Composable RowScope.() -> Unit)? = null,
    backgroundColor: Color = AppTheme.colors.surfaces
) {
    TopAppBar(
        modifier = modifier,
        backgroundColor = backgroundColor,
        elevation = 0.dp,
    ) {
        Box {
            leftActions?.let {
                Row(
                    Modifier.fillMaxSize(),
                    horizontalArrangement = Arrangement.Start,
                    verticalAlignment = Alignment.CenterVertically,
                    content = it
                )
            }
            title?.let {
                Row(
                    modifier = Modifier.fillMaxSize(),
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.Center,
                    content = it
                )
            }
            rightActions?.let {
                Row(
                    Modifier.fillMaxSize(),
                    horizontalArrangement = Arrangement.End,
                    verticalAlignment = Alignment.CenterVertically,
                    content = it
                )
            }
        }
    }
}