package com.progressterra.ipbandroidview.pages.goodsdetails

import androidx.compose.runtime.Immutable
import com.progressterra.ipbandroidview.features.goodsdescription.GoodsDescriptionState
import com.progressterra.ipbandroidview.features.itemgallery.ItemGalleryState
import com.progressterra.ipbandroidview.shared.ScreenState

@Immutable
data class GoodsDetailsState(
    val description: GoodsDescriptionState = GoodsDescriptionState(),
    val gallery: ItemGalleryState = ItemGalleryState(),
    val name: String = "",
    val screenState: ScreenState = ScreenState.LOADING
) {

    fun updateScreenState(screenState: ScreenState) = copy(screenState = screenState)
}