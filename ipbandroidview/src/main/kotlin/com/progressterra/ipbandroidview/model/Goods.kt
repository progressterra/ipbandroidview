package com.progressterra.ipbandroidview.model

import android.os.Parcelable
import com.progressterra.ipbandroidview.components.ColorsLineState
import com.progressterra.ipbandroidview.components.GalleryState
import com.progressterra.ipbandroidview.components.SizesLineState
import com.progressterra.ipbandroidview.components.StoreItemCardState
import com.progressterra.ipbandroidview.components.bottombar.GoodsBottomBarState
import com.progressterra.ipbandroidview.components.goodsdetails.GoodsDetailsState
import com.progressterra.ipbandroidview.components.topbar.GoodsTopAppBarState
import com.progressterra.ipbandroidview.model.component.Id
import kotlinx.parcelize.Parcelize

@Parcelize
data class Goods(
    override val id: String,
    override val name: String,
    override val description: String,
    override val image: String,
    override val images: List<String>,
    override val price: String,
    override val color: GoodsColor,
    override val colors: List<GoodsColor>,
    override val favorite: Boolean,
    override val size: GoodsSize,
    override val sizes: List<GoodsSize>,
    override val parameters: List<GoodsParameters>,
    override val inCartCounter: Int
) : Parcelable, StoreItemCardState, Id, GoodsTopAppBarState,
    GoodsBottomBarState,
    GalleryState,
    ColorsLineState,
    SizesLineState,
    GoodsDetailsState