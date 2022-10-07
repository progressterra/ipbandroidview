package com.progressterra.ipbandroidview.composable

import androidx.compose.foundation.layout.*
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.Text
import androidx.compose.material.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.progressterra.ipbandroidview.R
import com.progressterra.ipbandroidview.theme.AppTheme

private val navButtonSize = 32.dp

@Composable
fun ThemedTopAppBar(
    title: String,
    onBack: (() -> Unit)? = null,
    actions: (@Composable RowScope.() -> Unit)? = null
) {
    TopAppBar(
        backgroundColor = AppTheme.colors.surfaces,
        elevation = 0.dp,
        modifier = Modifier.fillMaxWidth()
    ) {
        Box {
            onBack?.let {
                Row(
                    modifier = Modifier.fillMaxSize(),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    IconButton(modifier = Modifier.size(navButtonSize), onClick = { it() }) {
                        Icon(
                            painter = painterResource(id = R.drawable.ic_back),
                            contentDescription = stringResource(id = R.string.navigate_back),
                            tint = AppTheme.colors.gray1
                        )
                    }
                }
            }
            Row(
                modifier = Modifier.fillMaxSize(),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.Center
            ) {
                Text(
                    text = title,
                    color = AppTheme.colors.black,
                    style = AppTheme.typography.title,
                    maxLines = 1,
                    textAlign = TextAlign.Center
                )
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