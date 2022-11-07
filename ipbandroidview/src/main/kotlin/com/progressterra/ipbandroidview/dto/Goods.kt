package com.progressterra.ipbandroidview.dto

import com.progressterra.ipbandroidview.components.ColorsLineState
import com.progressterra.ipbandroidview.components.GalleryState
import com.progressterra.ipbandroidview.components.SizesLineState
import com.progressterra.ipbandroidview.components.StoreItemCardState
import com.progressterra.ipbandroidview.dto.component.Id
import com.progressterra.ipbandroidview.dto.size.GoodsSize

data class Goods(
    override val id: String,
    override val name: String,
    val description: String,
    override val image: String,
    override val images: List<String>,
    override val price: String,
    override val color: GoodsColor,
    override val colors: List<GoodsColor>,
    override val favorite: Boolean,
    override val size: GoodsSize,
    override val sizes: List<GoodsSize>,
    val parameters: List<GoodsParameters>,
    val countInCart: Int
) : StoreItemCardState, SizesLineState, GalleryState, ColorsLineState, Id