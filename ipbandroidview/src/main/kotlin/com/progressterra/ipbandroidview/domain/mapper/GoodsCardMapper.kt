package com.progressterra.ipbandroidview.domain.mapper

import com.google.gson.Gson
import com.google.gson.annotations.SerializedName
import com.progressterra.ipbandroidapi.api.iecommerce.model.RGGoodsInventoryExt
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
                ).list.first().url,
                price = priceMapper.map(data.currentPrice!!),
                name = data.name!!,
                favorite = favoriteIds.contains(data.idUnique!!)
            )

        data class ImageData(
            @SerializedName("listInfoImage") val list: List<Item>
        ) {

            data class Item(
                @SerializedName("URL") val url: String
            )
        }
    }
}