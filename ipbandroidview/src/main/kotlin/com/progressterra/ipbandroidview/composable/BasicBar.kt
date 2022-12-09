package com.progressterra.ipbandroidview.composable

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.RowScope
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.progressterra.ipbandroidview.theme.AppTheme

private val minHeight = 56.dp

@Composable
fun BasicBar(
    modifier: Modifier = Modifier,
    backgroundColor: Color = AppTheme.colors.surfaces,
    paddingValues: PaddingValues,
    arrangement: Arrangement.Horizontal = Arrangement.SpaceBetween,
    content: @Composable RowScope.() -> Unit
) {
    Row(
        modifier = modifier
            .fillMaxWidth()
            .heightIn(min = minHeight)
            .background(backgroundColor)
            .padding(paddingValues),
        content = content,
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = arrangement
    )
}