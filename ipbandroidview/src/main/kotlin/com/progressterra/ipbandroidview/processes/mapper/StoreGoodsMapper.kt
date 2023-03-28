package com.progressterra.ipbandroidview.processes.mapper

import com.google.gson.Gson
import com.google.gson.annotations.SerializedName
import com.progressterra.ipbandroidapi.api.models.RGGoodsInventoryExt
import com.progressterra.ipbandroidview.R
import com.progressterra.ipbandroidview.core.AbstractMapper
import com.progressterra.ipbandroidview.core.DoubleMapper
import com.progressterra.ipbandroidview.core.ManageResources
import com.progressterra.ipbandroidview.entities.SimplePrice
import com.progressterra.ipbandroidview.composable.component.StoreCardComponentState

interface StoreGoodsMapper : DoubleMapper<RGGoodsInventoryExt, Boolean, StoreCardComponentState> {

    class Base(
        gson: Gson,
        manageResources: ManageResources,
        private val priceMapper: PriceMapper
    ) : StoreGoodsMapper, AbstractMapper(gson) {

        private val noData = manageResources.string(R.string.no_data)

        override fun map(data1: RGGoodsInventoryExt, data2: Boolean): StoreCardComponentState {
            val images = data1.imageDataJSON?.let { image ->
                gson.fromJson(
                    image, ImageData::class.java
                ).list
            } ?: emptyList()
            return StoreCardComponentState(id = data1.idUnique!!,
                image = images.firstOrNull()?.url ?: "",
                price = data1.currentPrice?.let { priceMapper.map(it) } ?: SimplePrice(),
                name = data1.name ?: noData,
                favorite = data2
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