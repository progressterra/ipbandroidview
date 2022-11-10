package com.progressterra.ipbandroidview.components.topbar

import androidx.compose.animation.animateContentSize
import androidx.compose.animation.core.LinearOutSlowInEasing
import androidx.compose.animation.core.tween
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.IconButton
import androidx.compose.material.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.progressterra.ipbandroidview.R
import com.progressterra.ipbandroidview.actions.Keyword
import com.progressterra.ipbandroidview.components.BackIcon
import com.progressterra.ipbandroidview.components.SettingsIcon
import com.progressterra.ipbandroidview.components.ThemedTextField
import com.progressterra.ipbandroidview.model.Filter
import com.progressterra.ipbandroidview.model.component.Filters
import com.progressterra.ipbandroidview.theme.AppTheme

interface SearchBarState : Keyword, Filters

//TODO filters counter

@Composable
fun SearchTopBar(
    state: SearchBarState,
    full: Boolean = true,
    onBack: () -> Unit,
    onKeyword: (String) -> Unit,
    onSearch: () -> Unit,
    onFilters: () -> Unit
) {
    TopAppBar(
        backgroundColor = AppTheme.colors.surfaces,
        elevation = 0.dp,
        modifier = Modifier.fillMaxWidth(),
        contentPadding = PaddingValues(start = 16.dp, end = 16.dp, bottom = 10.dp)
    ) {
        Row(
            Modifier.fillMaxSize(),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.spacedBy(16.dp)
        ) {
            if (full)
                IconButton(onClick = onBack) { BackIcon() }
            ThemedTextField(
                modifier = Modifier
                    .weight(1f)
                    .animateContentSize(
                        animationSpec = tween(
                            durationMillis = 300, easing = LinearOutSlowInEasing
                        )
                    ),
                text = state.keyword,
                hint = stringResource(id = R.string.search),
                onChange = { onKeyword(it) },
                keyboardOptions = KeyboardOptions.Default.copy(
                    imeAction = ImeAction.Search
                ),
                action = onSearch
            )
            if (full)
                IconButton(onClick = onFilters) { SettingsIcon() }
        }
    }
}

private class SearchBarStatePreview(
    override val keyword: String, override val filters: List<Filter>
) : SearchBarState

@Preview
@Composable
private fun SearchTopBarWithBackNavPreview0() {
    AppTheme {
        SearchTopBar(state = SearchBarStatePreview("Some keyword", emptyList()),
            onSearch = {},
            onBack = {},
            onKeyword = {},
            onFilters = {})
    }
}