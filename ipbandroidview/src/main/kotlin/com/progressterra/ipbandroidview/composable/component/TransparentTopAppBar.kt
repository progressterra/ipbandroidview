package com.progressterra.ipbandroidview.composable.component

import androidx.compose.foundation.layout.RowScope
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import com.progressterra.ipbandroidview.composable.element.BasicTopAppBar
import com.progressterra.ipbandroidview.theme.AppTheme

@Composable
fun TransparentTopAppBar(
    modifier: Modifier = Modifier,
    title: @Composable () -> String? = { null },
    leftActions: (@Composable RowScope.() -> Unit)? = null,
    rightActions: (@Composable RowScope.() -> Unit)? = null,
    contentColor: @Composable () -> Color = { AppTheme.colors.surfaces }
) {
    BasicTopAppBar(
        modifier = modifier,
        leftActions = leftActions,
        rightActions = rightActions,
        backgroundColor = Color.Transparent,
        title = {
            title()?.let {
                Text(
                    text = it,
                    color = contentColor(),
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
private fun TopAppBarWithBackNavPreview0() {
    AppTheme {
        TransparentTopAppBar(title = { "Some mock title" }, leftActions = {})
    }
}

@Preview
@Composable
private fun TopAppBarWithBackNavPreview1() {
    AppTheme {
        TransparentTopAppBar(title = { "Some mock title" })
    }
}

@Preview
@Composable
private fun TopAppBarWithBackNavPreview3() {
    AppTheme {
        TransparentTopAppBar(title = { "Some mock title" }, leftActions = {
            Text(
                text = "SOS",
                color = AppTheme.colors.surfaces,
                style = AppTheme.typography.title,
                maxLines = 1,
                textAlign = TextAlign.Center
            )
        }, rightActions = {
            Text(
                text = "SOS",
                color = AppTheme.colors.surfaces,
                style = AppTheme.typography.title,
                maxLines = 1,
                textAlign = TextAlign.Center
            )
        })
    }
}