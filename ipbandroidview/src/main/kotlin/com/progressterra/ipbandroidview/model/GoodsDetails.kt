package com.progressterra.ipbandroidview.model

import androidx.compose.runtime.Immutable
import com.progressterra.ipbandroidview.components.ColorsLineState
import com.progressterra.ipbandroidview.components.GalleryState
import com.progressterra.ipbandroidview.components.SizesLineState
import com.progressterra.ipbandroidview.components.GoodsBottomBarState
import com.progressterra.ipbandroidview.components.goodsdetails.GoodsDetailsState
import com.progressterra.ipbandroidview.components.bar.GoodsTopAppBarState

@Immutable
interface GoodsDetails : GoodsTopAppBarState,
    GoodsBottomBarState,
    GalleryState,
    ColorsLineState,
    SizesLineState,
    GoodsDetailsState {

    fun addOne(): GoodsDetails

    fun removeOne(): GoodsDetails

    fun reverseFavorite(): GoodsDetails

    data class Base(
        override val color: GoodsColor = GoodsColor(image = "", name = ""),
        override val colors: List<GoodsColor> = emptyList(),
        override val description: String = "",
        override val favorite: Boolean = false,
        override val images: List<String> = emptyList(),
        override val inCartCounter: Int = 0,
        override val name: String = "",
        override val parameters: List<GoodsParameters> = emptyList(),
        override val price: String = "",
        override val size: GoodsSize = GoodsSize(available = false, primary = "", secondary = null),
        override val sizes: List<GoodsSize> = emptyList()
    ) : GoodsDetails {

        override fun addOne(): GoodsDetails = this.copy(inCartCounter = inCartCounter + 1)

        override fun removeOne(): GoodsDetails = this.copy(inCartCounter = inCartCounter - 1)

        override fun reverseFavorite(): GoodsDetails = this.copy(favorite = !favorite)
    }
}