package com.progressterra.ipbandroidview.processes.mapper

import com.google.gson.Gson
import com.google.gson.annotations.SerializedName
import com.progressterra.ipbandroidapi.api.models.RGGoodsInventoryExt
import com.progressterra.ipbandroidview.R
import com.progressterra.ipbandroidview.entities.GoodsItem
import com.progressterra.ipbandroidview.entities.GoodsParameters
import com.progressterra.ipbandroidview.entities.SimplePrice
import com.progressterra.ipbandroidview.shared.AbstractMapper
import com.progressterra.ipbandroidview.shared.ManageResources

interface GoodsDetailsMapper {

    fun map(
        goodsRaw: RGGoodsInventoryExt, isFavorite: Boolean, count: Int
    ): GoodsItem

    class Base(
        gson: Gson, manageResources: ManageResources, private val priceMapper: PriceMapper
    ) : GoodsDetailsMapper, AbstractMapper(gson) {

        private val noData = manageResources.string(R.string.no_data)

        override fun map(
            goodsRaw: RGGoodsInventoryExt, isFavorite: Boolean, count: Int
        ): GoodsItem {
            val parsedParameters = parse<Map<String, String?>>(goodsRaw.additionalDataJSON)
            val parametersToShow = parsedParameters?.get("listVisible")?.split(",")
            val parameters: List<GoodsParameters> = buildList {
                parsedParameters?.forEach {
                    add(GoodsParameters(title = it.key, description = it.value ?: noData))
                }
            }.filter { parametersToShow?.contains(it.title) ?: false }
            val images = goodsRaw.imageDataJSON?.let { image ->
                gson.fromJson(
                    image, ImageData::class.java
                ).list
            } ?: emptyList()
            return GoodsItem(
                price = goodsRaw.currentPrice?.let { priceMapper.map(it) } ?: SimplePrice(),
                name = goodsRaw.name ?: noData,
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