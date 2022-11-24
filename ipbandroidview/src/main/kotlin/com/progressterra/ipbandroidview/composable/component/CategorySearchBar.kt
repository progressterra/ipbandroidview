package com.progressterra.ipbandroidview.composable.component

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.expandHorizontally
import androidx.compose.animation.shrinkHorizontally
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
import com.progressterra.ipbandroidview.composable.element.ThemedTextField
import com.progressterra.ipbandroidview.theme.AppTheme

@Immutable
interface CategorySearchBarState {

    val keyword: String

    val expanded: Boolean
}

private val paddingBottom = 10.dp

@Composable
fun CategorySearchBar(
    modifier: Modifier = Modifier,
    state: () -> CategorySearchBarState,
    category: () -> String,
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
        AnimatedVisibility(
            modifier = modifier,
            visible = !state().expanded,
            enter = expandHorizontally(),
            exit = shrinkHorizontally()
        ) { IconButton(onClick = onBack) { BackIcon() } }
        AnimatedVisibility(
            modifier = modifier,
            visible = !state().expanded,
            enter = expandHorizontally(),
            exit = shrinkHorizontally()
        ) {
            Text(
                text = category(),
                style = AppTheme.typography.title,
                color = AppTheme.colors.black
            )
        }
        AnimatedVisibility(
            modifier = modifier,
            visible = state().expanded,
            enter = expandHorizontally(),
            exit = shrinkHorizontally()
        ) {
            ThemedTextField(modifier = Modifier
                .weight(1f),
                text = state()::keyword,
                hint = stringResource(id = R.string.search),
                onChange = onKeyword,
                keyboardOptions = KeyboardOptions.Default.copy(
                    imeAction = ImeAction.Search
                ),
                action = onSearch,
                trailingIcon = {
                    if (state().keyword.isNotEmpty()) IconButton(onClick = onClear) {
                        Mark2Icon()
                    }
                    else SearchIcon()
                })
        }
        AnimatedVisibility(
            modifier = modifier,
            visible = !state().expanded,
            enter = expandHorizontally(),
            exit = shrinkHorizontally()
        ) {
            IconButton(onClick = onSearch) { SearchIcon() }
        }
    }
}

private class CategorySearchBarStatePreview(
    override val keyword: String, override val expanded: Boolean
) : CategorySearchBarState

@Preview
@Composable
private fun CategorySearchBarPreview() {
    AppTheme {
        CategorySearchBar(
            state = {
                CategorySearchBarStatePreview(
                    keyword = "", expanded = false
                )
            },
            onBack = {},
            onClear = {},
            onKeyword = {},
            onSearch = {},
            category = { "Some category" }
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
                    keyword = "some keyword", expanded = true
                )
            },
            onBack = {},
            onClear = {},
            onKeyword = {},
            onSearch = {},
            category = { "Some category" }
        )
    }
}