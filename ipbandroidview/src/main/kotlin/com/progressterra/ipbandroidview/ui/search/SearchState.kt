package com.progressterra.ipbandroidview.ui.search

import com.progressterra.ipbandroidview.components.SearchBoxState
import com.progressterra.ipbandroidview.components.topbar.SearchBarState
import com.progressterra.ipbandroidview.core.IsEmpty
import com.progressterra.ipbandroidview.core.ScreenState
import com.progressterra.ipbandroidview.model.Filter
import com.progressterra.ipbandroidview.model.Goods

data class SearchState(
    val categoryId: String? = null,
    override val filters: List<Filter> = emptyList(),
    override val keyword: String = "",
    override val searchScreenState: ScreenState = ScreenState.SUCCESS,
    override val searchGoods: List<Goods> = emptyList()
) : SearchBoxState, SearchBarState, IsEmpty {

    override val visible: Boolean get() = filters.isNotEmpty() && keyword.isNotBlank()

    fun clear(): SearchState = this.copy(
        filters = emptyList(),
        keyword = "",
        searchGoods = emptyList(),
        searchScreenState = ScreenState.SUCCESS
    )

    override fun isEmpty(): Boolean = filters.isEmpty() &&
            keyword == "" &&
            searchGoods.isEmpty() &&
            searchScreenState == ScreenState.SUCCESS
}