package com.progressterra.ipbandroidview.composable.component

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import com.progressterra.ipbandroidview.model.Message
import com.progressterra.ipbandroidview.theme.AppTheme

private val edgePadding: Dp = 40.dp

private val emptyPadding: Dp = 0.dp

@Composable
fun ChatMessage(
    modifier: Modifier = Modifier,
    message: () -> Message
) {
    val paddingValues = PaddingValues(
        start = if (message().user) edgePadding else emptyPadding,
        end = if (message().user) emptyPadding else edgePadding
    )
    Row(
        modifier = modifier.fillMaxWidth().padding(paddingValues),
        horizontalArrangement = if (message().user) Arrangement.End else Arrangement.Start
    ) {
        Column(
            modifier = modifier
                .clip(AppTheme.shapes.medium)
                .background(AppTheme.colors.surfaces)
                .padding(AppTheme.dimensions.medium),
            verticalArrangement = Arrangement.spacedBy(AppTheme.dimensions.tiniest)
        ) {
            Text(
                text = message().content,
                color = AppTheme.colors.black,
                style = AppTheme.typography.text
            )
            Text(
                text = message().date,
                color = AppTheme.colors.gray2,
                style = AppTheme.typography.tertiaryText
            )
        }
    }
}