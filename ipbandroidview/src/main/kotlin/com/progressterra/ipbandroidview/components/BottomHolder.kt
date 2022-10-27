package com.progressterra.ipbandroidview.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.progressterra.ipbandroidview.theme.AppTheme

@Composable
fun BottomHolder(modifier: Modifier = Modifier, content: @Composable ColumnScope.() -> Unit) {

    Column(
        modifier = modifier
            .clip(
                RoundedCornerShape(
                    topStart = AppTheme.roundings.largeRounding,
                    topEnd = AppTheme.roundings.largeRounding
                )
            )
            .background(AppTheme.colors.surfaces)
            .padding(8.dp), content = content
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
            Spacer(modifier = Modifier.size(8.dp))
            ThemedTextButton(
                modifier = Modifier.fillMaxWidth(), onClick = { }, text = "Some button 2"
            )
        }
    }
}