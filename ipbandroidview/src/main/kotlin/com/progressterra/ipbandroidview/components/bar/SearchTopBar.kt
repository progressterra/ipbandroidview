package com.progressterra.ipbandroidview.components.bar

import androidx.compose.animation.animateContentSize
import androidx.compose.animation.core.LinearOutSlowInEasing
import androidx.compose.animation.core.tween
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.IconButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.Immutable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.tooling.preview.Preview
import com.progressterra.ipbandroidview.R
import com.progressterra.ipbandroidview.components.BackIcon
import com.progressterra.ipbandroidview.components.SettingsIcon
import com.progressterra.ipbandroidview.components.ThemedTextField
import com.progressterra.ipbandroidview.core.IsEmpty
import com.progressterra.ipbandroidview.model.Filter
import com.progressterra.ipbandroidview.model.component.Filters
import com.progressterra.ipbandroidview.model.component.Keyword
import com.progressterra.ipbandroidview.theme.AppTheme

@Immutable
interface SearchBarState : Keyword, Filters, IsEmpty

@Composable
fun SearchTopBar(
    modifier: Modifier = Modifier,
    state: () -> SearchBarState,
    showFilter: Boolean = true,
    showBack: Boolean = true,
    onBack: () -> Unit,
    onKeyword: (String) -> Unit,
    onSearch: () -> Unit,
    onFilters: () -> Unit
) {
    BasicBar(
        modifier = modifier
    ) {
        Row(
            modifier = Modifier.padding(bottom = AppTheme.dimensions.small),
            verticalAlignment = Alignment.CenterVertically
        ) {
            if (showBack || !state().isEmpty())
                IconButton(onClick = onBack) { BackIcon() }
            ThemedTextField(
                modifier = Modifier
                    .weight(1f)
                    .animateContentSize(
                        animationSpec = tween(
                            durationMillis = 300, easing = LinearOutSlowInEasing
                        )
                    ),
                text = state()::keyword,
                hint = stringResource(id = R.string.search),
                onChange = onKeyword,
                keyboardOptions = KeyboardOptions.Default.copy(
                    imeAction = ImeAction.Search
                ),
                action = onSearch
            )
            if (showFilter || !state().isEmpty())
                IconButton(onClick = onFilters) { SettingsIcon() }
        }
    }
}

private class SearchBarStatePreview(
    override val keyword: String, override val filters: List<Filter>
) : SearchBarState {

    override fun isEmpty(): Boolean = false
}

@Preview
@Composable
private fun SearchTopBarWithBackNavPreview0() {
    AppTheme {
        SearchTopBar(state = { SearchBarStatePreview("Some keyword", emptyList()) },
            onSearch = {},
            onBack = {},
            onKeyword = {},
            onFilters = {})
    }
}