package com.progressterra.ipbandroidview.composable

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import com.progressterra.ipbandroidview.theme.AppTheme

private val padding: Dp = 8.dp
private val rounding: Dp = 20.dp

@Composable
fun BottomHolder(modifier: Modifier = Modifier, content: @Composable ColumnScope.() -> Unit) {

    Column(
        modifier = modifier
            .clip(RoundedCornerShape(topStart = rounding, topEnd = rounding))
            .background(AppTheme.colors.surfaces)
            .padding(padding), content = content
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