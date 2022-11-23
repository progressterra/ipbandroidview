package com.progressterra.ipbandroidview.domain.mapper

import com.google.gson.Gson
import com.google.gson.annotations.SerializedName
import com.progressterra.ipbandroidapi.api.models.RGGoodsInventoryExt
import com.progressterra.ipbandroidview.R
import com.progressterra.ipbandroidview.core.AbstractMapper
import com.progressterra.ipbandroidview.core.ManageResources
import com.progressterra.ipbandroidview.model.CartGoods
import com.progressterra.ipbandroidview.model.GoodsColor
import com.progressterra.ipbandroidview.model.GoodsSize
import com.progressterra.ipbandroidview.model.SimplePrice

interface CartGoodsMapper {

    fun map(data: RGGoodsInventoryExt, isFavorite: Boolean, count: Int): CartGoods

    class Base(
        gson: Gson, manageResources: ManageResources, private val priceMapper: PriceMapper
    ) : CartGoodsMapper, AbstractMapper(gson) {

        private val noData = manageResources.string(R.string.no_data)

        override fun map(data: RGGoodsInventoryExt, isFavorite: Boolean, count: Int): CartGoods {
            val images = data.imageDataJSON?.let { image ->
                gson.fromJson(
                    image, ImageData::class.java
                ).list
            } ?: emptyList()
            return CartGoods(
                id = data.idUnique!!,
                image = images.firstOrNull()?.url ?: "",
                price = data.currentPrice?.let { priceMapper.map(it) } ?: SimplePrice(),
                name = data.name ?: noData,
                favorite = isFavorite,
                inCartCounter = count,
                color = GoodsColor(image = "", name = data.colorName ?: noData),
                size = GoodsSize(true, "", null),
            )
        }

        data class ImageData(
            @SerializedName("datalist") val list: List<Item>
        ) {

            data class Item(
                @SerializedName("URL") val url: String
            )
        }
    }
}