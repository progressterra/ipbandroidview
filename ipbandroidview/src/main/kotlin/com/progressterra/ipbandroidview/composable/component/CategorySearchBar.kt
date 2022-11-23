package com.progressterra.ipbandroidview.composable.component

import androidx.compose.animation.animateContentSize
import androidx.compose.animation.core.LinearEasing
import androidx.compose.animation.core.tween
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.IconButton
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.Immutable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.progressterra.ipbandroidview.R
import com.progressterra.ipbandroidview.composable.element.BackIcon
import com.progressterra.ipbandroidview.composable.element.BasicBar
import com.progressterra.ipbandroidview.composable.element.Mark2Icon
import com.progressterra.ipbandroidview.composable.element.SearchIcon
import com.progressterra.ipbandroidview.composable.element.SettingsIcon
import com.progressterra.ipbandroidview.composable.element.ThemedTextField
import com.progressterra.ipbandroidview.model.Filter
import com.progressterra.ipbandroidview.theme.AppTheme

@Immutable
interface CategorySearchBarState {

    val keyword: String

    val expanded: Boolean

    val filters: List<Filter>
}

private val paddingBottom = 10.dp

@Composable
fun CategorySearchBar(
    modifier: Modifier = Modifier,
    state: () -> CategorySearchBarState,
    category: (() -> String)? = null,
    onBack: () -> Unit,
    onClear: () -> Unit,
    onKeyword: (String) -> Unit,
    onSearch: () -> Unit,
    onFilters: () -> Unit
) {
    BasicBar(
        modifier = modifier,
        paddingValues = PaddingValues(
            start = AppTheme.dimensions.large,
            end = AppTheme.dimensions.large,
            bottom = paddingBottom
        )
    ) {
        if (state().keyword.isNotEmpty() || category != null)
            IconButton(onClick = onBack) { BackIcon() }
        if (category != null && state().keyword.isEmpty())
            Text(
                text = category(),
                style = AppTheme.typography.title,
                color = AppTheme.colors.black
            )
        else
            ThemedTextField(
                modifier = Modifier
                    .weight(1f)
                    .animateContentSize(
                        animationSpec = tween(
                            durationMillis = 500, easing = LinearEasing
                        )
                    ),
                text = state()::keyword,
                hint = stringResource(id = R.string.search),
                onChange = onKeyword,
                keyboardOptions = KeyboardOptions.Default.copy(
                    imeAction = ImeAction.Search
                ),
                action = onSearch,
                trailingIcon = {
                    if (state().keyword.isNotEmpty())
                        IconButton(onClick = onClear) {
                            Mark2Icon()
                        }
                    else
                        SearchIcon()
                }
            )
        if (state().keyword.isNotEmpty())
            IconButton(onClick = onSearch) { SearchIcon() }
        if (state().expanded)
            IconButton(onClick = onFilters) { SettingsIcon() }
    }
}

private class CategorySearchBarStatePreview(
    override val keyword: String, override val expanded: Boolean,
    override val filters: List<Filter>
) : CategorySearchBarState

@Preview
@Composable
private fun CategorySearchBarPreview() {
    AppTheme {
        CategorySearchBar(
            state = {
                CategorySearchBarStatePreview(
                    keyword = "", expanded = false, filters = emptyList()
                )
            },
            onBack = {},
            onClear = {},
            onKeyword = {},
            onFilters = {},
            onSearch = {}
        )
    }
}

@Preview
@Composable
private fun CategorySearchBarPreviewExpanded() {
    AppTheme {
        CategorySearchBar(
            state = {
                CategorySearchBarStatePreview(
                    keyword = "some keyword", expanded = true, filters = emptyList()
                )
            },
            onBack = {},
            onClear = {},
            onKeyword = {},
            onFilters = {},
            onSearch = {}
        )
    }
}