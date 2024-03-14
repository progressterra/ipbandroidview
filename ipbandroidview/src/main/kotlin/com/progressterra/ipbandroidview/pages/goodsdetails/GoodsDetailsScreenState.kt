package com.progressterra.ipbandroidview.pages.goodsdetails

import com.progressterra.ipbandroidview.entities.Id
import com.progressterra.ipbandroidview.features.buygoods.BuyGoodsState
import com.progressterra.ipbandroidview.features.goodsdescription.GoodsDescriptionState
import com.progressterra.ipbandroidview.features.itemgallery.ItemGalleryState
import com.progressterra.ipbandroidview.shared.ui.statecolumn.StateColumnState
import com.progressterra.ipbandroidview.widgets.galleries.GalleriesState


data class GoodsDetailsScreenState(
    override val id: String = "",
    val description: GoodsDescriptionState = GoodsDescriptionState(),
    val gallery: ItemGalleryState = ItemGalleryState(),
    val similarGoods: GalleriesState = GalleriesState(),
    val buyGoods: BuyGoodsState = BuyGoodsState(),
    val name: String = "",
    val screen: StateColumnState = StateColumnState(),
    val rating: Double = 0.0
) : Id {
    companion object
}