package com.progressterra.ipbandroidview.pages.goodsdetails

import androidx.compose.runtime.Immutable
import com.progressterra.ipbandroidview.entities.Id
import com.progressterra.ipbandroidview.features.buygoods.BuyGoodsState
import com.progressterra.ipbandroidview.features.goodsdescription.GoodsDescriptionState
import com.progressterra.ipbandroidview.features.goodsdescription.uFavoriteButtonFavorite
import com.progressterra.ipbandroidview.features.itemgallery.ItemGalleryState
import com.progressterra.ipbandroidview.shared.ScreenState
import com.progressterra.ipbandroidview.widgets.galleries.GalleriesState

@Immutable
data class GoodsDetailsState(
    val description: GoodsDescriptionState = GoodsDescriptionState(),
    val gallery: ItemGalleryState = ItemGalleryState(),
    val similarGoods: GalleriesState = GalleriesState(),
    val buyGoods: BuyGoodsState = BuyGoodsState(),
    val name: String = "",
    override val id: String = "",
    val screenState: ScreenState = ScreenState.LOADING
) : Id {

    fun uId(newId: String) = copy(id = newId)

    fun uScreenState(screenState: ScreenState) = copy(screenState = screenState)

    fun uDescriptionFavoriteButtonState(newFavorite: Boolean) =
        copy(description = description.uFavoriteButtonFavorite(newFavorite))
}