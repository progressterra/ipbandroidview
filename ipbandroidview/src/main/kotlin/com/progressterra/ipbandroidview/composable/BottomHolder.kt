package com.progressterra.ipbandroidview.composable

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.ColumnScope
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.ZeroCornerSize
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.tooling.preview.Preview
import com.progressterra.ipbandroidview.theme.AppTheme

@Composable
fun BottomHolder(modifier: Modifier = Modifier, content: @Composable ColumnScope.() -> Unit) {
    Column(
        modifier = modifier
            .clip(
                AppTheme.shapes.large.copy(bottomStart = ZeroCornerSize, bottomEnd = ZeroCornerSize)
            )
            .background(AppTheme.colors.surfaces)
            .padding(AppTheme.dimensions.small), content = content
    )
}

@Preview
@Composable
private fun BottomHolderPreview() {
    AppTheme {
        BottomHolder {
            ThemedButton(
                modifier = Modifier.fillMaxWidth(),
                onClick = { },
                text = "Some button 1",
            )
            Spacer(modifier = Modifier.size(AppTheme.dimensions.small))
            ThemedTextButton(
                modifier = Modifier.fillMaxWidth(), onClick = { }, text = "Some button 2"
            )
        }
    }
}