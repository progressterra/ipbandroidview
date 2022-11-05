package com.progressterra.ipbandroidview.domain.mapper

import com.google.gson.Gson
import com.google.gson.annotations.SerializedName
import com.progressterra.ipbandroidapi.api.iecommerce.model.RGGoodsInventoryExt
import com.progressterra.ipbandroidview.R
import com.progressterra.ipbandroidview.core.AbstractMapper
import com.progressterra.ipbandroidview.core.ManageResources
import com.progressterra.ipbandroidview.dto.Goods
import com.progressterra.ipbandroidview.dto.GoodsColor
import com.progressterra.ipbandroidview.dto.GoodsParameters
import com.progressterra.ipbandroidview.dto.size.GoodsSize

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
            val images = data.imageDataJSON?.let { image ->
                gson.fromJson(
                    image, ImageData::class.java
                ).list
            } ?: emptyList()
            return Goods(id = data.idUnique!!,
                image = images.firstOrNull { it.sizeType == 2 }?.url ?: "",
                images = images.filter { it.sizeType == 3 }.map { it.url },
                price = data.currentPrice?.let { priceMapper.map(it) } ?: noData,
                name = data.name ?: noData,
                favorite = favoriteIds.contains(data.idUnique!!),
                description = data.extendedDescription ?: noData,
                parameters = parameters,
                countInCart = data.countInCart ?: 0,
                color = GoodsColor(image = "", name = data.colorName ?: noData),
                size = GoodsSize(TODO(), TODO(), TODO()),
                sizes = emptyList()
            )
        }
        //TODO IMAGE COLOR

        data class ImageData(
            @SerializedName("listInfoImage") val list: List<Item>
        ) {

            data class Item(
                @SerializedName("URL") val url: String,
                @SerializedName("SizeType") val sizeType: Int
            )
        }
    }
}