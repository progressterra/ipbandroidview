package com.progressterra.ipbandroidview.ui.search

import androidx.compose.runtime.Immutable
import com.progressterra.ipbandroidview.composable.component.CategorySearchBarState
import com.progressterra.ipbandroidview.composable.component.SearchBoxState
import com.progressterra.ipbandroidview.core.ScreenState
import com.progressterra.ipbandroidview.model.Filter
import com.progressterra.ipbandroidview.model.StoreGoods

@Immutable
data class SearchState(
    val categoryId: String? = null,
    override val filters: List<Filter> = emptyList(),
    override val keyword: String = "",
    override val searchScreenState: ScreenState = ScreenState.SUCCESS,
    override val searchGoods: List<StoreGoods> = emptyList(),
    override val expanded: Boolean = false
) : SearchBoxState, CategorySearchBarState {

    fun clear(): SearchState = this.copy(
        filters = emptyList(),
        keyword = "",
        searchGoods = emptyList(),
        searchScreenState = ScreenState.SUCCESS
    )

    override fun isEmpty(): Boolean = filters.isEmpty() &&
            keyword == "" &&
            searchGoods.isEmpty()
}