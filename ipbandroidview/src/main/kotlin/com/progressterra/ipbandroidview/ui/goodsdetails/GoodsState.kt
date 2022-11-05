package com.progressterra.ipbandroidview.ui.goodsdetails

import androidx.compose.runtime.Immutable
import com.progressterra.ipbandroidview.components.ColorsLineState
import com.progressterra.ipbandroidview.components.GalleryState
import com.progressterra.ipbandroidview.components.SizesLineState
import com.progressterra.ipbandroidview.components.bottombar.GoodsBottomBarState
import com.progressterra.ipbandroidview.components.goodsdetails.GoodsDetailsState
import com.progressterra.ipbandroidview.components.topbar.GoodsTopAppBarState
import com.progressterra.ipbandroidview.dto.Goods

@Immutable
data class GoodsState(
    val topBarState: GoodsTopAppBarState,
    val bottomBarState: GoodsBottomBarState,
    val galleryState: GalleryState,
    val colorsLineState: ColorsLineState,
    val sizesState: SizesLineState,
    val detailsState: GoodsDetailsState
) {
    constructor(goods: Goods) : this(
        topBarState = GoodsTopAppBarState(
            name = goods.name,
            price = goods.price,
            favorite = goods.favorite
        ),
        bottomBarState = GoodsBottomBarState(goods.price, goods.countInCart),
        galleryState = goods,
        colorsLineState = ColorsLineState(goods.color, emptyList()),
        sizesState = goods,
        detailsState = GoodsDetailsState(
            name = goods.name,
            description = goods.description,
            parameters = goods.parameters
        )
    )
}
