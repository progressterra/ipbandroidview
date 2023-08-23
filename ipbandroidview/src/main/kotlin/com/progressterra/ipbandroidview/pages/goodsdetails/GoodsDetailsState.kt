package com.progressterra.ipbandroidview.pages.goodsdetails

import androidx.compose.runtime.Immutable
import com.progressterra.ipbandroidview.entities.Id
import com.progressterra.ipbandroidview.features.buygoods.BuyGoodsState
import com.progressterra.ipbandroidview.features.goodsdescription.GoodsDescriptionState
import com.progressterra.ipbandroidview.features.itemgallery.ItemGalleryState
import com.progressterra.ipbandroidview.shared.ui.statebox.StateBoxState
import com.progressterra.ipbandroidview.widgets.galleries.GalleriesState

@Immutable
data class GoodsDetailsState(
    override val id: String = "",
    val description: GoodsDescriptionState = GoodsDescriptionState(),
    val gallery: ItemGalleryState = ItemGalleryState(),
    val similarGoods: GalleriesState = GalleriesState(),
    val buyGoods: BuyGoodsState = BuyGoodsState(),
    val name: String = "",
    val screen: StateBoxState = StateBoxState()
) : Id {
    companion object
}