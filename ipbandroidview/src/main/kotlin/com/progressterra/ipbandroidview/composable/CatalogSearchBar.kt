package com.progressterra.ipbandroidview.composable

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.IconButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.Immutable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.progressterra.ipbandroidview.R
import com.progressterra.ipbandroidview.theme.AppTheme

@Immutable
interface CatalogSearchBarState {

    val keyword: String
}

private val paddingBottom = 10.dp

@Composable
fun CatalogSearchBar(
    modifier: Modifier = Modifier,
    state: () -> CatalogSearchBarState,
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
}

private class CatalogSearchBarStatePreview(
    override val keyword: String
) : CatalogSearchBarState

@Preview
@Composable
private fun CatalogSearchBarPreview() {
    AppTheme {
        CatalogSearchBar(
            state = {
                CatalogSearchBarStatePreview(
                    keyword = ""
                )
            },
            onClear = {},
            onKeyword = {},
            onSearch = {}
        )
    }
}

@Preview
@Composable
private fun CatalogSearchBarPreviewExpanded() {
    AppTheme {
        CatalogSearchBar(
            state = {
                CatalogSearchBarStatePreview(
                    keyword = "some keyword"
                )
            },
            onClear = {},
            onKeyword = {},
            onSearch = {}
        )
    }
}