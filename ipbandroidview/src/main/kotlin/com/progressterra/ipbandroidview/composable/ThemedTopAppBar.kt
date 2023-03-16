package com.progressterra.ipbandroidview.composable

import androidx.compose.foundation.layout.RowScope
import androidx.compose.material.IconButton
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import com.progressterra.ipbandroidview.shared.theme.IpbTheme

@Composable
fun ThemedTopAppBar(
    modifier: Modifier = Modifier,
    title: String,
    onBack: (() -> Unit)? = null,
    actions: (@Composable RowScope.() -> Unit)? = null,
) {
    BasicTopAppBar(
        modifier = modifier,
        leftActions = {
            onBack?.let {
                IconButton(onClick = it) {
                    BackIcon()
                }
            }
        }, title = {
            Text(
                text = title,
                color = IpbTheme.colors.black,
                style = IpbTheme.typography.title,
                maxLines = 1,
                textAlign = TextAlign.Center
            )
        }, rightActions = actions
    )
}

@Preview
@Composable
private fun TopAppBarWithBackNavPreview0() {
    IpbTheme {
        ThemedTopAppBar(title = "Some mock title", onBack = {})
    }
}

@Preview
@Composable
private fun TopAppBarWithBackNavPreview1() {
    IpbTheme {
        ThemedTopAppBar(title = "Some mock title")
    }
}

@Preview
@Composable
private fun TopAppBarWithBackNavPreview2() {
    IpbTheme {
        ThemedTopAppBar(title = "Some mock title", actions = {
            Text(
                text = "SOS",
                color = IpbTheme.colors.black,
                style = IpbTheme.typography.title,
                maxLines = 1,
                textAlign = TextAlign.Center
            )
        })
    }
}

@Preview
@Composable
private fun TopAppBarWithBackNavPreview3() {
    IpbTheme {
        ThemedTopAppBar(title = "Some mock title", onBack = {}, actions = {
            Text(
                text = "SOS",
                color = IpbTheme.colors.black,
                style = IpbTheme.typography.title,
                maxLines = 1,
                textAlign = TextAlign.Center
            )
        })
    }
}