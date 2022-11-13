package com.progressterra.ipbandroidview.domain.mapper

import com.google.gson.Gson
import com.google.gson.annotations.SerializedName
import com.progressterra.ipbandroidapi.api.models.RGGoodsInventoryExt
import com.progressterra.ipbandroidview.R
import com.progressterra.ipbandroidview.core.AbstractMapper
import com.progressterra.ipbandroidview.core.ManageResources
import com.progressterra.ipbandroidview.model.GoodsColor
import com.progressterra.ipbandroidview.model.GoodsDetails
import com.progressterra.ipbandroidview.model.GoodsParameters
import com.progressterra.ipbandroidview.model.GoodsSize

interface GoodsDetailsMapper {

    fun map(data: RGGoodsInventoryExt, isFavorite: Boolean): GoodsDetails

    class Base(
        gson: Gson, manageResources: ManageResources, private val priceMapper: PriceMapper
    ) : GoodsDetailsMapper, AbstractMapper(gson) {

        private val noData = manageResources.string(R.string.no_data)

        override fun map(data: RGGoodsInventoryExt, isFavorite: Boolean): GoodsDetails {
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
            return GoodsDetails.Base(
                images = images.map { it.url },
                price = data.currentPrice?.let { priceMapper.map(it) } ?: noData,
                name = data.name ?: noData,
                favorite = isFavorite,
                description = data.extendedDescription ?: noData,
                parameters = parameters,
                inCartCounter = data.countInCart ?: 0,
                color = GoodsColor(image = "", name = data.colorName ?: noData),
                size = GoodsSize(true, "", null),
                sizes = emptyList(),
                colors = emptyList()
            )
        }
        //TODO IMAGE COLOR, SIZE

        data class ImageData(
            @SerializedName("datalist") val list: List<Item>
        ) {

            data class Item(
                @SerializedName("URL") val url: String
            )
        }
    }
}