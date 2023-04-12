package com.progressterra.ipbandroidview.features.storecard

import com.google.gson.Gson
import com.google.gson.annotations.SerializedName
import com.progressterra.ipbandroidapi.api.models.RGGoodsInventoryExt
import com.progressterra.ipbandroidview.entities.SimplePrice
import com.progressterra.ipbandroidview.shared.AbstractMapper
import com.progressterra.ipbandroidview.shared.Mapper

interface StoreCardMapper : Mapper<RGGoodsInventoryExt, StoreCardState> {

    class Base(
        gson: Gson
    ) : StoreCardMapper, AbstractMapper(gson) {

        override fun map(data: RGGoodsInventoryExt): StoreCardState =
            StoreCardState(
                name = data.name ?: "",
                price = SimplePrice(data.currentPrice ?: 0.0),
                imageUrl = data.imageDataJSON?.let { image ->
                    gson.fromJson(
                        image, ImageData::class.java
                    ).list
                }?.firstOrNull()?.url ?: ""
            )

        data class ImageData(
            @SerializedName("datalist") val list: List<Item>
        ) {

            data class Item(
                @SerializedName("URL") val url: String
            )
        }
    }
}