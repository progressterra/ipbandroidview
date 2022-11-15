package com.progressterra.ipbandroidview.ui.search

import androidx.compose.runtime.Immutable
import com.progressterra.ipbandroidview.components.SearchBoxState
import com.progressterra.ipbandroidview.components.bar.SearchBarState
import com.progressterra.ipbandroidview.core.ScreenState
import com.progressterra.ipbandroidview.model.Filter
import com.progressterra.ipbandroidview.model.StoreGoods

@Immutable
data class SearchState(
    val categoryId: String? = null,
    override val filters: List<Filter> = emptyList(),
    override val keyword: String = "",
    override val searchScreenState: ScreenState = ScreenState.SUCCESS,
    override val searchGoods: List<StoreGoods> = emptyList()
) : SearchBoxState, SearchBarState {

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