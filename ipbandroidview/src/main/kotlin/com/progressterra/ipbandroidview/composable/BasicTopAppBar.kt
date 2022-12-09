package com.progressterra.ipbandroidview.composable

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.RowScope
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import com.progressterra.ipbandroidview.theme.AppTheme

@Composable
fun BasicTopAppBar(
    modifier: Modifier = Modifier,
    leftActions: (@Composable RowScope.() -> Unit)? = null,
    title: (@Composable RowScope.() -> Unit)? = null,
    rightActions: (@Composable RowScope.() -> Unit)? = null,
    backgroundColor: Color = AppTheme.colors.surfaces
) {
    BasicBar(
        modifier = modifier,
        backgroundColor = backgroundColor,
        paddingValues = PaddingValues(horizontal = AppTheme.dimensions.large)
    ) {
        Box(contentAlignment = Alignment.Center) {
            leftActions?.let {
                Row(
                    Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.Start,
                    verticalAlignment = Alignment.CenterVertically,
                    content = it
                )
            }
            title?.let {
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.Center,
                    content = it
                )
            }
            rightActions?.let {
                Row(
                    Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.End,
                    verticalAlignment = Alignment.CenterVertically,
                    content = it
                )
            }
        }
    }
}