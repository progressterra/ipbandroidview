package com.progressterra.ipbandroidview.components.topbar

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.RowScope
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.Text
import androidx.compose.material.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.progressterra.ipbandroidview.theme.AppTheme

@Composable
fun TransparentTopAppBar(
    modifier: Modifier = Modifier,
    title: String? = null,
    onBack: (@Composable RowScope.() -> Unit)? = null,
    actions: (@Composable RowScope.() -> Unit)? = null,
    contentColor: Color = AppTheme.colors.surfaces
) {
    TopAppBar(
        backgroundColor = Color.Transparent,
        elevation = 0.dp,
        modifier = modifier,
        contentPadding = PaddingValues(horizontal = 16.dp)
    ) {
        Box {
            onBack?.let {
                Row(
                    Modifier.fillMaxSize(),
                    horizontalArrangement = Arrangement.Start,
                    verticalAlignment = Alignment.CenterVertically,
                    content = it
                )
            }
            title?.let {
                Row(
                    modifier = Modifier.fillMaxSize(),
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.Center
                ) {
                    Text(
                        text = it,
                        color = contentColor,
                        style = AppTheme.typography.title,
                        maxLines = 1,
                        textAlign = TextAlign.Center
                    )
                }
            }
            actions?.let {
                Row(
                    Modifier.fillMaxSize(),
                    horizontalArrangement = Arrangement.End,
                    verticalAlignment = Alignment.CenterVertically,
                    content = it
                )
            }
        }
    }
}

@Preview
@Composable
private fun TopAppBarWithBackNavPreview0() {
    AppTheme {
        TransparentTopAppBar(title = "Some mock title", onBack = {})
    }
}

@Preview
@Composable
private fun TopAppBarWithBackNavPreview1() {
    AppTheme {
        TransparentTopAppBar(title = "Some mock title")
    }
}

@Preview
@Composable
private fun TopAppBarWithBackNavPreview3() {
    AppTheme {
        TransparentTopAppBar(title = "Some mock title", onBack = {
            Text(
                text = "SOS",
                color = AppTheme.colors.surfaces,
                style = AppTheme.typography.title,
                maxLines = 1,
                textAlign = TextAlign.Center
            )
        }, actions = {
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