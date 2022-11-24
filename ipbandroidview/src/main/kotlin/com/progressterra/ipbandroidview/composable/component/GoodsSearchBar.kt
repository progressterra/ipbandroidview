package com.progressterra.ipbandroidview.composable.component

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.Immutable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.progressterra.ipbandroidview.R
import com.progressterra.ipbandroidview.composable.element.BasicBar
import com.progressterra.ipbandroidview.composable.element.Mark2Icon
import com.progressterra.ipbandroidview.composable.element.SearchIcon
import com.progressterra.ipbandroidview.composable.element.ThemedTextField
import com.progressterra.ipbandroidview.theme.AppTheme

@Immutable
interface GoodsSearchBarState {

    val keyword: String
}

private val paddingBottom = 10.dp

@Composable
fun GoodsSearchBar(
    modifier: Modifier = Modifier,
    state: () -> GoodsSearchBarState,
    onBack: () -> Unit,
    onFilters: () -> Unit,
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
            Icon(
                painter = painterResource(id = R.drawable.ic_back),
                contentDescription = stringResource(id = R.string.navigate_back),
                tint = AppTheme.colors.gray1
            )
        }
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
        IconButton(onClick = onFilters) {
            Icon(
                painter = painterResource(id = R.drawable.ic_back),
                contentDescription = stringResource(id = R.string.navigate_back),
                tint = AppTheme.colors.gray1
            )
        }
    }
}

private class GoodsSearchBarStatePreview(
    override val keyword: String
) : GoodsSearchBarState

@Preview
@Composable
private fun GoodsSearchBarPreview() {
    AppTheme {
        GoodsSearchBar(
            state = {
                GoodsSearchBarStatePreview(
                    keyword = ""
                )
            },
            onClear = {},
            onKeyword = {},
            onSearch = {},
            onBack = {},
            onFilters = {}
        )
    }
}

@Preview
@Composable
private fun GoodsSearchBarPreviewExpanded() {
    AppTheme {
        GoodsSearchBar(
            state = {
                GoodsSearchBarStatePreview(
                    keyword = "some keyword"
                )
            },
            onClear = {},
            onKeyword = {},
            onSearch = {},
            onBack = {},
            onFilters = {}
        )
    }
}