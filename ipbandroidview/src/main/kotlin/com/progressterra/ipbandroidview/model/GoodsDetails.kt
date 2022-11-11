package com.progressterra.ipbandroidview.model

import com.progressterra.ipbandroidview.components.ColorsLineState
import com.progressterra.ipbandroidview.components.GalleryState
import com.progressterra.ipbandroidview.components.SizesLineState
import com.progressterra.ipbandroidview.components.bottombar.GoodsBottomBarState
import com.progressterra.ipbandroidview.components.goodsdetails.GoodsDetailsState
import com.progressterra.ipbandroidview.components.topbar.GoodsTopAppBarState
import com.progressterra.ipbandroidview.model.component.Id

interface GoodsDetails : Id,
    GoodsTopAppBarState,
    GoodsBottomBarState,
    GalleryState,
    ColorsLineState,
    SizesLineState,
    GoodsDetailsState {

    class Base(
        override val color: GoodsColor,
        override val colors: List<GoodsColor>,
        override val description: String,
        override val favorite: Boolean,
        override val id: String,
        override val images: List<String>,
        override val inCartCounter: Int,
        override val name: String,
        override val parameters: List<GoodsParameters>,
        override val price: String,
        override val size: GoodsSize,
        override val sizes: List<GoodsSize>
    ) : GoodsDetails
}