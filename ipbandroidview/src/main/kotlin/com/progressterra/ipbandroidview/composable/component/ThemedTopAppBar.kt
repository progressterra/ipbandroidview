package com.progressterra.ipbandroidview.composable.component

import androidx.compose.foundation.layout.RowScope
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import com.progressterra.ipbandroidview.R
import com.progressterra.ipbandroidview.composable.element.BackIcon
import com.progressterra.ipbandroidview.composable.element.BasicTopAppBar
import com.progressterra.ipbandroidview.theme.AppTheme

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
                color = AppTheme.colors.black,
                style = AppTheme.typography.title,
                maxLines = 1,
                textAlign = TextAlign.Center
            )
        }, rightActions = actions
    )
}

@Preview
@Composable
private fun TopAppBarWithBackNavPreview0() {
    AppTheme {
        ThemedTopAppBar(title = "Some mock title", onBack = {})
    }
}

@Preview
@Composable
private fun TopAppBarWithBackNavPreview1() {
    AppTheme {
        ThemedTopAppBar(title = "Some mock title")
    }
}

@Preview
@Composable
private fun TopAppBarWithBackNavPreview2() {
    AppTheme {
        ThemedTopAppBar(title = "Some mock title", actions = {
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
private fun TopAppBarWithBackNavPreview3() {
    AppTheme {
        ThemedTopAppBar(title = "Some mock title", onBack = {}, actions = {
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