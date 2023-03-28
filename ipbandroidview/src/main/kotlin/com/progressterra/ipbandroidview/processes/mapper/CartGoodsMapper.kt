package com.progressterra.ipbandroidview.processes.mapper

import com.google.gson.Gson
import com.google.gson.annotations.SerializedName
import com.progressterra.ipbandroidapi.api.models.RGGoodsInventoryExt
import com.progressterra.ipbandroidview.R
import com.progressterra.ipbandroidview.core.AbstractMapper
import com.progressterra.ipbandroidview.core.ManageResources
import com.progressterra.ipbandroidview.core.TripleMapper
import com.progressterra.ipbandroidview.composable.component.CartCardState
import com.progressterra.ipbandroidview.entities.GoodsSize
import com.progressterra.ipbandroidview.entities.SimplePrice

interface CartGoodsMapper : TripleMapper<RGGoodsInventoryExt, Boolean, Int, CartCardState> {

    class Base(
        gson: Gson, manageResources: ManageResources, private val priceMapper: PriceMapper
    ) : CartGoodsMapper, AbstractMapper(gson) {

        private val noData = manageResources.string(R.string.no_data)

        override fun map(data1: RGGoodsInventoryExt, data2: Boolean, data3: Int): CartCardState {
            val images = data1.imageDataJSON?.let { image ->
                gson.fromJson(
                    image, ImageData::class.java
                ).list
            } ?: emptyList()
            return CartCardState(
                id = data1.idUnique!!,
                image = images.firstOrNull()?.url ?: "",
                price = data1.currentPrice?.let { priceMapper.map(it) } ?: SimplePrice(),
                name = data1.name ?: noData,
                favorite = data2,
                inCartCounter = data3,
                color = data1.colorName ?: noData,
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