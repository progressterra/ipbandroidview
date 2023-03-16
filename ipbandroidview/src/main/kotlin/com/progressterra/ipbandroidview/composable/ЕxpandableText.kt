package com.progressterra.ipbandroidview.composable

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
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import com.progressterra.ipbandroidview.composable.utils.niceClickable
import com.progressterra.ipbandroidview.shared.theme.IpbTheme

@Composable
fun ExpandableText(
    modifier: Modifier = Modifier,
    text: String,
    expanded: Boolean,
    expand: () -> Unit,
    content: @Composable ColumnScope.() -> Unit
) {
    Column(
        modifier = modifier
            .clip(IpbTheme.shapes.medium)
            .background(IpbTheme.colors.surfaces)
    ) {
        Row(
            modifier = Modifier
                .clip(IpbTheme.shapes.medium)
                .fillMaxWidth()
                .niceClickable { expand() }
                .padding(
                    horizontal = 12.dp, vertical = 16.dp
                ),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text(
                text = text, style = IpbTheme.typography.title, color = IpbTheme.colors.black
            )
            if (expanded) UpIcon()
            else DownIcon()
        }
        AnimatedVisibility(
            visible = expanded, enter = expandVertically(), exit = shrinkVertically()
        ) {
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(12.dp),
                verticalArrangement = Arrangement.spacedBy(12.dp),
                content = content
            )
        }
    }
}