package com.progressterra.ipbandroidview.components.topbar

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.IconButton
import androidx.compose.material.Text
import androidx.compose.material.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.progressterra.ipbandroidview.R
import com.progressterra.ipbandroidview.components.BackIcon
import com.progressterra.ipbandroidview.components.SettingsIcon
import com.progressterra.ipbandroidview.components.ThemedTextField
import com.progressterra.ipbandroidview.theme.AppTheme

@Composable
fun SearchTopBar(
    state: SearchBarState,
    onBack: () -> Unit,
    onKeyword: (String) -> Unit,
    onSearch: () -> Unit,
    onFilters: () -> Unit
) {
    TopAppBar(
        backgroundColor = AppTheme.colors.surfaces,
        elevation = 0.dp,
        modifier = Modifier.fillMaxWidth(),
        contentPadding = PaddingValues(horizontal = 16.dp)
    ) {
        Row(
            Modifier.fillMaxSize(),
            verticalAlignment = Alignment.CenterVertically,
        ) {
            AnimatedVisibility(visible = state.expanded) {
                IconButton(onClick = onBack) {
                    BackIcon()
                }
            }
            ThemedTextField(
                text = state.keyword,
                hint = stringResource(id = R.string.search),
                onChange = { onKeyword(it) },
                keyboardOptions = KeyboardOptions.Default.copy(
                    imeAction = ImeAction.Search
                )
            )
            AnimatedVisibility(visible = state.expanded) {
                IconButton(onClick = onFilters) {
                    SettingsIcon()
                }
            }
        }
    }
}

@Preview
@Composable
private fun SearchTopBarWithBackNavPreview0() {
    AppTheme {
        SearchTopBar(title = "Some mock title")
    }
}

@Preview
@Composable
private fun SearchTopBarWithBackNavPreview1() {
    AppTheme {
        SearchTopBar(title = "Some mock title", rightActions = {
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
private fun SearchTopBarWithBackNavPreview2() {
    AppTheme {
        SearchTopBar(title = "Some mock title", leftActions = {
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
private fun SearchTopBarWithBackNavPreview3() {
    AppTheme {
        SearchTopBar(title = "Some mock title", leftActions = {
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