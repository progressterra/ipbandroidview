package com.progressterra.ipbandroidview.ui.goodsdetails

import com.progressterra.ipbandroidview.components.ColorsLineState
import com.progressterra.ipbandroidview.components.GalleryState
import com.progressterra.ipbandroidview.components.SizesLineState
import com.progressterra.ipbandroidview.components.bottombar.GoodsBottomBarState
import com.progressterra.ipbandroidview.components.goodsdetails.GoodsDetailsState
import com.progressterra.ipbandroidview.components.topbar.GoodsTopAppBarState
import com.progressterra.ipbandroidview.model.Goods
import com.progressterra.ipbandroidview.model.GoodsColor
import com.progressterra.ipbandroidview.model.GoodsParameters
import com.progressterra.ipbandroidview.model.component.Id
import com.progressterra.ipbandroidview.model.GoodsSize


data class GoodsDetailsScreenState(
    override val color: GoodsColor = GoodsColor("", ""),
    override val colors: List<GoodsColor> = emptyList(),
    override val description: String = "",
    override val favorite: Boolean = false,
    override val images: List<String> = emptyList(),
    override val inCartCounter: Int = 0,
    override val name: String = "",
    override val parameters: List<GoodsParameters> = emptyList(),
    override val price: String = "",
    override val size: GoodsSize = GoodsSize(false, "", null),
    override val sizes: List<GoodsSize> = emptyList(),
    override val id: String = ""
) :
    GoodsTopAppBarState,
    GoodsBottomBarState,
    GalleryState,
    ColorsLineState,
    SizesLineState,
    GoodsDetailsState, Id {

    constructor(goods: Goods) : this(
        color = goods.color,
        colors = goods.colors,
        description = goods.description,
        favorite = goods.favorite,
        images = goods.images,
        inCartCounter = goods.inCartCounter,
        name = goods.name,
        parameters = goods.parameters,
        price = goods.price,
        size = goods.size,
        sizes = goods.sizes,
        id = goods.id
    )
}
