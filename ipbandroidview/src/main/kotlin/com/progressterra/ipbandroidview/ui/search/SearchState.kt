package com.progressterra.ipbandroidview.ui.search

import com.progressterra.ipbandroidview.components.SearchBoxState
import com.progressterra.ipbandroidview.components.topbar.SearchBarState
import com.progressterra.ipbandroidview.core.ScreenState
import com.progressterra.ipbandroidview.dto.Filter
import com.progressterra.ipbandroidview.dto.Goods

data class SearchState(
    val categoryId: String? = null,
    override val filters: List<Filter> = emptyList(),
    override val keyword: String = "",
    override val searchScreenState: ScreenState = ScreenState.LOADING,
    override val searchGoods: List<Goods> = emptyList()
) : SearchBoxState, SearchBarState {

    override val full: Boolean get() = categoryId != null

    override val visible: Boolean get() = filters.isNotEmpty() && keyword.isNotBlank()
}