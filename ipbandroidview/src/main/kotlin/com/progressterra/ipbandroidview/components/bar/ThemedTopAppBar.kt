package com.progressterra.ipbandroidview.components.bar

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
import com.progressterra.ipbandroidview.theme.AppTheme

@Composable
fun ThemedTopAppBar(
    modifier: Modifier = Modifier,
    title: @Composable () -> String? = { null },
    onBack: (() -> Unit)? = null,
    actions: (@Composable RowScope.() -> Unit)? = null,
) {
    BasicTopAppBar(
        modifier = modifier,
        leftActions = {
            onBack?.let {
                IconButton(onClick = it) {
                    Icon(
                        painter = painterResource(id = R.drawable.ic_back),
                        contentDescription = stringResource(id = R.string.navigate_back),
                        tint = AppTheme.colors.gray1
                    )
                }
            }
        }, title = {
            title()?.let {
                Text(
                    text = it,
                    color = AppTheme.colors.black,
                    style = AppTheme.typography.title,
                    maxLines = 1,
                    textAlign = TextAlign.Center
                )
            }
        }, rightActions = actions
    )
}

@Preview
@Composable
private fun TopAppBarWithBackNavPreview0() {
    AppTheme {
        ThemedTopAppBar(title = { "Some mock title" }, onBack = {})
    }
}

@Preview
@Composable
private fun TopAppBarWithBackNavPreview1() {
    AppTheme {
        ThemedTopAppBar(title = { "Some mock title" })
    }
}

@Preview
@Composable
private fun TopAppBarWithBackNavPreview2() {
    AppTheme {
        ThemedTopAppBar(title = { "Some mock title" }, actions = {
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
        ThemedTopAppBar(title = { "Some mock title" }, onBack = {}, actions = {
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
private fun TopAppBarWithBackNavPreview4() {
    AppTheme {
        ThemedTopAppBar(onBack = {}, actions = {
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