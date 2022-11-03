package com.progressterra.ipbandroidview.domain.mapper

import com.google.gson.Gson
import com.google.gson.annotations.SerializedName
import com.progressterra.ipbandroidapi.api.iecommerce.model.RGGoodsInventoryExt
import com.progressterra.ipbandroidview.R
import com.progressterra.ipbandroidview.core.AbstractMapper
import com.progressterra.ipbandroidview.core.ManageResources
import com.progressterra.ipbandroidview.dto.Goods
import com.progressterra.ipbandroidview.dto.GoodsParameters

interface GoodsCardMapper {

    fun map(data: RGGoodsInventoryExt, favoriteIds: List<String>): Goods

    class Base(
        gson: Gson, manageResources: ManageResources, private val priceMapper: PriceMapper
    ) : GoodsCardMapper, AbstractMapper(gson) {

        private val noData = manageResources.string(R.string.no_data)

        override fun map(data: RGGoodsInventoryExt, favoriteIds: List<String>): Goods {
            val parsedParameters = parse<Map<String, String?>>(data.additionalDataJSON)
            val parametersToShow = parsedParameters?.get("listVisible")?.split(",")
            val parameters: List<GoodsParameters> = buildList {
                parsedParameters?.forEach {
                    add(GoodsParameters(title = it.key, description = it.value ?: noData))
                }
            }.filter { parametersToShow?.contains(it.title) ?: false }
            return Goods(id = data.idUnique!!,
                images = data.imageDataJSON?.let { image ->
                    gson.fromJson(
                        image, ImageData::class.java
                    ).list.map { it.url }
                } ?: emptyList(),
                price = data.currentPrice?.let { priceMapper.map(it) } ?: noData,
                name = data.name ?: noData,
                favorite = favoriteIds.contains(data.idUnique!!),
                description = data.extendedDescription ?: noData,
                parameters = parameters,
                countInCart = data.countInCart?.toString() ?: noData
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