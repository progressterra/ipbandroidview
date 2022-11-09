package com.progressterra.ipbandroidview.ui.goodsdetails

import com.progressterra.ipbandroidview.components.ColorsLineState
import com.progressterra.ipbandroidview.components.GalleryState
import com.progressterra.ipbandroidview.components.SizesLineState
import com.progressterra.ipbandroidview.components.bottombar.GoodsBottomBarState
import com.progressterra.ipbandroidview.components.goodsdetails.GoodsDetailsState
import com.progressterra.ipbandroidview.components.topbar.GoodsTopAppBarState
import com.progressterra.ipbandroidview.dto.Goods
import com.progressterra.ipbandroidview.dto.GoodsColor
import com.progressterra.ipbandroidview.dto.GoodsParameters
import com.progressterra.ipbandroidview.dto.size.GoodsSize


data class GoodsDetailsScreenState(
    private val goods: Goods? = null
) :
    GoodsTopAppBarState,
    GoodsBottomBarState,
    GalleryState,
    ColorsLineState,
    SizesLineState,
    GoodsDetailsState {

    override val color: GoodsColor = goods?.color ?: GoodsColor("", "")
    override val colors: List<GoodsColor> = goods?.colors ?: emptyList()
    override val description: String = goods?.description ?: ""
    override val favorite: Boolean = goods?.favorite ?: false
    override val images: List<String> = goods?.images ?: emptyList()
    override val inCartCounter: Int = goods?.inCartCounter ?: 0
    override val name: String = goods?.name ?: ""
    override val parameters: List<GoodsParameters> = goods?.parameters ?: emptyList()
    override val price: String = goods?.price ?: ""
    override val size: GoodsSize = goods?.size ?: GoodsSize(false, "", null)
    override val sizes: List<GoodsSize> = goods?.sizes ?: emptyList()
}
