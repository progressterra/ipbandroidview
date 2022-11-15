package com.progressterra.ipbandroidview.components.bar

import androidx.compose.foundation.layout.RowScope
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import com.progressterra.ipbandroidview.theme.AppTheme

@Composable
fun ThemedTopDialogBar(
    modifier: Modifier = Modifier,
    title: @Composable () -> String? = { null },
    leftActions: (@Composable RowScope.() -> Unit)? = null,
    rightActions: (@Composable RowScope.() -> Unit)? = null
) {
    BasicTopAppBar(modifier = modifier,
        leftActions = leftActions,
        rightActions = rightActions,
        title = {
            title()?.let {
                Text(
                    text = it,
                    color = AppTheme.colors.black,
                    style = AppTheme.typography.title,
                    maxLines = 1,
                    textAlign = TextAlign.Center
                )
            }
        }
    )
}

@Preview
@Composable
private fun TopDialogBarWithBackNavPreview0() {
    AppTheme {
        ThemedTopDialogBar(title = { "Some mock title" })
    }
}

@Preview
@Composable
private fun TopDialogBarWithBackNavPreview1() {
    AppTheme {
        ThemedTopDialogBar(title = { "Some mock title" }, rightActions = {
            Text(
                text = "SOS",
                color = AppTheme.colors.black,
                style = AppTheme.typography.title,
                maxLines = 1,
                textAlign = TextAlign.Center
            )
        })
    }
}

@Preview
@Composable
private fun TopDialogBarWithBackNavPreview2() {
    AppTheme {
        ThemedTopDialogBar(title = { "Some mock title" }, leftActions = {
            Text(
                text = "SOS",
                color = AppTheme.colors.black,
                style = AppTheme.typography.title,
                maxLines = 1,
                textAlign = TextAlign.Center
            )
        })
    }
}

@Preview
@Composable
private fun TopDialogBarWithBackNavPreview3() {
    AppTheme {
        ThemedTopDialogBar(title = { "Some mock title" }, leftActions = {
            Text(
                text = "SOS",
                color = AppTheme.colors.black,
                style = AppTheme.typography.title,
                maxLines = 1,
                textAlign = TextAlign.Center
            )
        }, rightActions = {
            Text(
                text = "SOS",
                color = AppTheme.colors.black,
                style = AppTheme.typography.title,
                maxLines = 1,
                textAlign = TextAlign.Center
            )
        })
    }
}