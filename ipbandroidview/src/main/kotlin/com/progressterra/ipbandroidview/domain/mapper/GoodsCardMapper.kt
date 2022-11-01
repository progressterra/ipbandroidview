package com.progressterra.ipbandroidview.domain.mapper

import com.google.gson.Gson
import com.progressterra.ipbandroidapi.api.iecommerce.model.RGGoodsInventoryExt
import com.progressterra.ipbandroidapi.api.ipbmediadata.model.ImageData
import com.progressterra.ipbandroidview.dto.GoodsCard

interface GoodsCardMapper {

    fun map(data: RGGoodsInventoryExt, favoriteIds: List<String>): GoodsCard

    class Base(
        private val gson: Gson, private val priceMapper: PriceMapper
    ) : GoodsCardMapper {

        override fun map(data: RGGoodsInventoryExt, favoriteIds: List<String>): GoodsCard =
            GoodsCard(
                id = data.idUnique!!,
                imageUri = gson.fromJson(
                    data.imageDataJSON!!, ImageData::class.java
                ).list.first { it.sizeType == 2 }.url,
                price = priceMapper.map(data.currentPrice!!),
                name = data.name!!,
                favorite = favoriteIds.contains(data.idUnique!!)
            )
    }
}