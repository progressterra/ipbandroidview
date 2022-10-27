package com.progressterra.ipbandroidview.components

import androidx.compose.foundation.layout.*
import androidx.compose.material.Text
import androidx.compose.material.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.progressterra.ipbandroidview.theme.AppTheme

@Composable
fun ThemedTopDialogBar(
    title: String? = null,
    leftActions: (@Composable RowScope.() -> Unit)? = null,
    rightActions: (@Composable RowScope.() -> Unit)? = null
) {
    TopAppBar(
        backgroundColor = AppTheme.colors.surfaces,
        elevation = 0.dp,
        modifier = Modifier.fillMaxWidth(),
        contentPadding = PaddingValues(horizontal = 16.dp)
    ) {
        Box {
            leftActions?.let {
                Row(
                    Modifier.fillMaxSize(),
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
                        color = AppTheme.colors.black,
                        style = AppTheme.typography.title,
                        maxLines = 1,
                        textAlign = TextAlign.Center
                    )
                }
            }
            rightActions?.let {
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
private fun TopDialogBarWithBackNavPreview0() {
    AppTheme {
        ThemedTopDialogBar(title = "Some mock title")
    }
}

@Preview
@Composable
private fun TopDialogBarWithBackNavPreview1() {
    AppTheme {
        ThemedTopDialogBar(title = "Some mock title", rightActions = {
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
        ThemedTopDialogBar(title = "Some mock title", leftActions = {
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
        ThemedTopDialogBar(title = "Some mock title", leftActions = {
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