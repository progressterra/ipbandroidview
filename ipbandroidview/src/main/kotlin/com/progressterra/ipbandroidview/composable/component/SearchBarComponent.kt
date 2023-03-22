package com.progressterra.ipbandroidview.composable.component

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.IconButton
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.Immutable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.unit.dp
import com.progressterra.ipbandroidview.composable.BackIcon
import com.progressterra.ipbandroidview.composable.BasicBar
import com.progressterra.ipbandroidview.composable.Mark2Icon
import com.progressterra.ipbandroidview.composable.SearchIcon
import com.progressterra.ipbandroidview.shared.theme.IpbTheme
import com.progressterra.ipbandroidview.shared.ui.TextField
import com.progressterra.ipbandroidview.shared.ui.TextFieldState
import com.progressterra.ipbandroidview.shared.ui.UseTextField

data class CatalogBarComponentState(
    val keywordState: TextFieldState = TextFieldState()
)

sealed class CatalogBarComponentEvent {

    object OnClear : CatalogBarComponentEvent()

    object OnSearch : CatalogBarComponentEvent()
}

interface UseCatalogBarComponent : UseTextField {

    fun handleEvent(id: String, event: CatalogBarComponentEvent)
}

private val paddingBottom = 10.dp

/**
 * keyword - TextFieldComponent
 */
@Composable
fun CatalogBarComponent(
    modifier: Modifier = Modifier,
    id: String,
    state: CatalogBarComponentState,
    useComponent: UseCatalogBarComponent
) {
    BasicBar(
        modifier = modifier, paddingValues = PaddingValues(
            start = 16.dp,
            end = 16.dp,
            bottom = paddingBottom
        )
    ) {
        TextField(modifier = Modifier.weight(1f),
            id = "keyword",
            keyboardOptions = KeyboardOptions.Default.copy(
                imeAction = ImeAction.Search
            ),
            state = state.keywordState,
            useComponent = useComponent,
            trailingIcon = {
                if (state.keywordState.text.isNotEmpty()) IconButton(onClick = {
                    useComponent.handleEvent(
                        id, CatalogBarComponentEvent.OnClear
                    )
                }) {
                    Mark2Icon()
                }
                else IconButton(onClick = {
                    useComponent.handleEvent(
                        id, CatalogBarComponentEvent.OnSearch
                    )
                }) {
                    SearchIcon()
                }
            })
    }
}

@Immutable
data class CategoryBarState(
    val keywordState: TextFieldState = TextFieldState(),
    val category: String,
    val expanded: Boolean
)

sealed class CategoryBarEvent {

    object OnBack : CategoryBarEvent()

    object OnExpand : CategoryBarEvent()

    object OnClear : CategoryBarEvent()
}

interface UseCategoryBarComponent : UseTextField {

    fun handleEvent(id: String, event: CategoryBarEvent)
}

@Composable
fun CategoryBar(
    modifier: Modifier = Modifier,
    id: String,
    state: CategoryBarState,
    useComponent: UseCategoryBarComponent
) {
    BasicBar(
        modifier = modifier, paddingValues = PaddingValues(
            start = 16.dp,
            end = 16.dp,
            bottom = paddingBottom
        )
    ) {
        if (!state.expanded) {
            IconButton(onClick = { useComponent.handleEvent(id, CategoryBarEvent.OnBack) }) {
                BackIcon()
            }
        }
        if (!state.expanded) {
            Text(
                text = state.category,
                style = IpbTheme.typography.title,
                color = IpbTheme.colors.black
            )
        }
        if (state.expanded) {
            TextField(
                modifier = Modifier.weight(1f),
                id = "keyword",
                state = state.keywordState,
                keyboardOptions = KeyboardOptions.Default.copy(
                    imeAction = ImeAction.Search
                ),
                trailingIcon = {
                    if (state.keywordState.text.isNotEmpty()) IconButton(onClick = {
                        useComponent.handleEvent(
                            id, CategoryBarEvent.OnClear
                        )
                    }) {
                        Mark2Icon()
                    }
                    else SearchIcon()
                },
                useComponent = useComponent
            )
        }
        if (!state.expanded) IconButton(onClick = {
            useComponent.handleEvent(
                id, CategoryBarEvent.OnExpand
            )
        }) {
            SearchIcon()
        }
    }
}

data class GoodsBarComponentState(
    val keyword: TextFieldState = TextFieldState()
)

interface UseGoodsBarComponent : UseTextField {

    fun handleEvent(id: String, event: GoodsBarComponentEvent)
}

sealed class GoodsBarComponentEvent {

    object OnBack : GoodsBarComponentEvent()

    object OnClear : GoodsBarComponentEvent()

    object OnSearch : GoodsBarComponentEvent()
}

/**
 * Filters may be here
 */
@Composable
fun GoodsSearchBar(
    modifier: Modifier = Modifier,
    id: String,
    state: GoodsBarComponentState,
    useComponent: UseGoodsBarComponent,
) {
    BasicBar(
        modifier = modifier, paddingValues = PaddingValues(
            start = 16.dp,
            end = 16.dp,
            bottom = paddingBottom
        )
    ) {
        IconButton(onClick = { useComponent.handleEvent(id, GoodsBarComponentEvent.OnBack) }) {
            BackIcon()
        }
        TextField(modifier = Modifier.weight(1f),
            id = "keyword",
            state = state.keyword,
            useComponent = useComponent,
            keyboardOptions = KeyboardOptions.Default.copy(
                imeAction = ImeAction.Search
            ),
            trailingIcon = {
                if (state.keyword.text.isNotEmpty()) IconButton(onClick = {
                    useComponent.handleEvent(
                        id, GoodsBarComponentEvent.OnClear
                    )
                }) {
                    Mark2Icon()
                }
                else IconButton(onClick = {
                    useComponent.handleEvent(
                        id, GoodsBarComponentEvent.OnSearch
                    )
                }) {
                    SearchIcon()
                }
            })
    }
}