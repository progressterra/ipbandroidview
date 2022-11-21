package com.progressterra.ipbandroidview.composable.element

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.expandVertically
import androidx.compose.animation.shrinkVertically
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.ColumnScope
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import com.progressterra.ipbandroidview.composable.utils.niceClickable
import com.progressterra.ipbandroidview.theme.AppTheme

@Composable
fun ExpandableText(
    modifier: Modifier = Modifier,
    text: () -> String,
    expanded: () -> Boolean,
    expand: () -> Unit,
    content: @Composable ColumnScope.() -> Unit
) {
    Column(
        modifier = modifier
            .clip(AppTheme.shapes.medium)
            .background(AppTheme.colors.surfaces)
    ) {
        Row(
            modifier = Modifier
                .clip(AppTheme.shapes.medium)
                .fillMaxWidth()
                .niceClickable(onClick = expand)
                .padding(
                    horizontal = AppTheme.dimensions.medium, vertical = AppTheme.dimensions.large
                ), horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Text(
                text = text(), style = AppTheme.typography.title, color = AppTheme.colors.black
            )
            if (expanded()) UpIcon()
            else DownIcon()
        }
        AnimatedVisibility(
            visible = expanded(), enter = expandVertically(), exit = shrinkVertically()
        ) {
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(
                        start = AppTheme.dimensions.medium,
                        bottom = AppTheme.dimensions.medium,
                        end = AppTheme.dimensions.medium
                    ), content = content
            )
        }
    }
}