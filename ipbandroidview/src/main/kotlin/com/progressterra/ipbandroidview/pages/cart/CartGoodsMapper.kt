package com.progressterra.ipbandroidview.pages.cart

import com.google.gson.Gson
import com.google.gson.annotations.SerializedName
import com.progressterra.ipbandroidapi.api.models.RGGoodsInventoryExt
import com.progressterra.ipbandroidview.R
import com.progressterra.ipbandroidview.entities.SimplePrice
import com.progressterra.ipbandroidview.features.cartcard.CartCardState
import com.progressterra.ipbandroidview.processes.mapper.PriceMapper
import com.progressterra.ipbandroidview.shared.AbstractMapper
import com.progressterra.ipbandroidview.shared.ManageResources
import com.progressterra.ipbandroidview.shared.Mapper

interface CartGoodsMapper : Mapper<RGGoodsInventoryExt, CartCardState> {

    class Base(
        gson: Gson, manageResources: ManageResources, private val priceMapper: PriceMapper
    ) : CartGoodsMapper, AbstractMapper(gson) {

        private val noData = manageResources.string(R.string.no_data)

        override fun map(data: RGGoodsInventoryExt): CartCardState {
            val images = data.imageDataJSON?.let { image ->
                gson.fromJson(
                    image, ImageData::class.java
                ).list
            } ?: emptyList()
            return CartCardState(id = data.idUnique!!,
                imageUrl = images.firstOrNull()?.url ?: "",
                price = data.currentPrice?.let { priceMapper.map(it) } ?: SimplePrice(),
                name = data.name ?: noData)
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