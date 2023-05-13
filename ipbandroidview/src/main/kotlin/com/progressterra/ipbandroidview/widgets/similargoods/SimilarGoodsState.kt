package com.progressterra.ipbandroidview.widgets.similargoods

import androidx.compose.runtime.Immutable
import com.progressterra.ipbandroidview.features.storecard.StoreCardState

@Immutable
data class SimilarGoodsState(
    val items: List<StoreCardState> = emptyList()
) {

    fun uItems(items: List<StoreCardState>) = copy(items = items)
}