package com.progressterra.ipbandroidview.composable.component

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.IconButton
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.Immutable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.unit.dp
import com.progressterra.ipbandroidview.R
import com.progressterra.ipbandroidview.composable.BackIcon
import com.progressterra.ipbandroidview.composable.BasicBar
import com.progressterra.ipbandroidview.composable.Mark2Icon
import com.progressterra.ipbandroidview.composable.SearchIcon
import com.progressterra.ipbandroidview.theme.AppTheme

//TODO: embed this in a single component

@Immutable
interface CatalogSearchBarState {

    val keyword: String
}

private val paddingBottom = 10.dp

@Composable
fun CatalogSearchBar(
    modifier: Modifier = Modifier,
    state: CatalogSearchBarState,
    onClear: () -> Unit,
    onKeyword: (String) -> Unit,
    onSearch: () -> Unit
) {
    BasicBar(
        modifier = modifier, paddingValues = PaddingValues(
            start = AppTheme.dimensions.large,
            end = AppTheme.dimensions.large,
            bottom = paddingBottom
        )
    ) {
        TextFieldComponent(modifier = Modifier.weight(1f),
            text = state.keyword,
            hint = stringResource(id = R.string.search),
            onChange = onKeyword,
            keyboardOptions = KeyboardOptions.Default.copy(
                imeAction = ImeAction.Search
            ),
            action = onSearch,
            trailingIcon = {
                if (state.keyword.isNotEmpty()) IconButton(onClick = onClear) {
                    Mark2Icon()
                }
                else SearchIcon()
            })
    }
}

@Immutable
interface CategorySearchBarState {

    val keyword: String

    val expanded: Boolean
}

@Composable
fun CategorySearchBar(
    modifier: Modifier = Modifier,
    state: CategorySearchBarState,
    category: String,
    onExpand: () -> Unit,
    onBack: () -> Unit,
    onClear: () -> Unit,
    onKeyword: (String) -> Unit,
    onSearch: () -> Unit
) {
    BasicBar(
        modifier = modifier, paddingValues = PaddingValues(
            start = AppTheme.dimensions.large,
            end = AppTheme.dimensions.large,
            bottom = paddingBottom
        )
    ) {
        if (!state.expanded) {
            IconButton(onClick = onBack) { BackIcon() }
        }
        if (!state.expanded) {
            Text(
                text = category, style = AppTheme.typography.title, color = AppTheme.colors.black
            )
        }
        if (state.expanded) {
            TextFieldComponent(modifier = Modifier.weight(1f),
                text = state.keyword,
                hint = stringResource(id = R.string.search),
                onChange = onKeyword,
                keyboardOptions = KeyboardOptions.Default.copy(
                    imeAction = ImeAction.Search
                ),
                action = onSearch,
                trailingIcon = {
                    if (state.keyword.isNotEmpty()) IconButton(onClick = onClear) {
                        Mark2Icon()
                    }
                    else SearchIcon()
                })
        }
        if (!state.expanded) IconButton(onClick = onExpand) { SearchIcon() }
    }
}

@Immutable
interface GoodsSearchBarState {

    val keyword: String
}

/**
 * Filters may be here
 */
@Composable
fun GoodsSearchBar(
    modifier: Modifier = Modifier,
    state: GoodsSearchBarState,
    onBack: () -> Unit,
    onClear: () -> Unit,
    onKeyword: (String) -> Unit,
    onSearch: () -> Unit
) {
    BasicBar(
        modifier = modifier, paddingValues = PaddingValues(
            start = AppTheme.dimensions.large,
            end = AppTheme.dimensions.large,
            bottom = paddingBottom
        )
    ) {
        IconButton(onClick = onBack) {
            BackIcon()
        }
        TextFieldComponent(modifier = Modifier.weight(1f),
            text = state.keyword,
            hint = stringResource(id = R.string.search),
            onChange = onKeyword,
            keyboardOptions = KeyboardOptions.Default.copy(
                imeAction = ImeAction.Search
            ),
            action = onSearch,
            trailingIcon = {
                if (state.keyword.isNotEmpty()) IconButton(onClick = onClear) {
                    Mark2Icon()
                }
                else SearchIcon()
            })
    }
}