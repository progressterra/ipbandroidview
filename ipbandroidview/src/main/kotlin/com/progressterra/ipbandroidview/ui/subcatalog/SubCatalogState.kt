package com.progressterra.ipbandroidview.ui.subcatalog

import androidx.compose.runtime.Immutable
import com.progressterra.ipbandroidview.core.ScreenState
import com.progressterra.ipbandroidview.dto.Filter
import com.progressterra.ipbandroidview.dto.Goods
import com.progressterra.ipbandroidview.dto.SubCategory

@Immutable
data class SubCatalogState(
    val currentCategory: SubCategory = SubCategory(
        id = "",
        name = "",
        subCategories = emptyList(),
        hasNext = false
    ),
    override val filters: List<Filter> = emptyList(),
    override val searchGoods: List<Goods> = emptyList(),
    override val keyword: String = "",
    override val screenState: ScreenState = ScreenState.LOADING
) : SearchState