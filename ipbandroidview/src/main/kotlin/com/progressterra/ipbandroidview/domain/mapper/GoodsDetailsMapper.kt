package com.progressterra.ipbandroidview.domain.mapper

import com.google.gson.Gson
import com.google.gson.annotations.SerializedName
import com.progressterra.ipbandroidapi.api.models.RGGoodsInventoryExt
import com.progressterra.ipbandroidview.R
import com.progressterra.ipbandroidview.core.AbstractMapper
import com.progressterra.ipbandroidview.core.ManageResources
import com.progressterra.ipbandroidview.core.TripleMapper
import com.progressterra.ipbandroidview.model.store.GoodsColor
import com.progressterra.ipbandroidview.model.store.GoodsDetails
import com.progressterra.ipbandroidview.model.store.GoodsParameters
import com.progressterra.ipbandroidview.model.store.GoodsSize
import com.progressterra.ipbandroidview.model.store.SimplePrice

interface GoodsDetailsMapper : TripleMapper<RGGoodsInventoryExt, Boolean, Int, GoodsDetails> {

    class Base(
        gson: Gson, manageResources: ManageResources, private val priceMapper: PriceMapper
    ) : GoodsDetailsMapper, AbstractMapper(gson) {

        private val noData = manageResources.string(R.string.no_data)

        override fun map(data1: RGGoodsInventoryExt, data2: Boolean, data3: Int): GoodsDetails {
            val parsedParameters = parse<Map<String, String?>>(data1.additionalDataJSON)
            val parametersToShow = parsedParameters?.get("listVisible")?.split(",")
            val parameters: List<GoodsParameters> = buildList {
                parsedParameters?.forEach {
                    add(GoodsParameters(title = it.key, description = it.value ?: noData))
                }
            }.filter { parametersToShow?.contains(it.title) ?: false }
            val images = data1.imageDataJSON?.let { image ->
                gson.fromJson(
                    image, ImageData::class.java
                ).list
            } ?: emptyList()
            return GoodsDetails(
                images = images.map { it.url },
                price = data1.currentPrice?.let { priceMapper.map(it) } ?: SimplePrice(),
                name = data1.name ?: noData,
                favorite = data2,
                description = data1.extendedDescription ?: noData,
                parameters = parameters,
                inCartCounter = data3,
                color = GoodsColor(image = "", name = data1.colorName ?: noData),
                size = GoodsSize(true, "", null),
                sizes = emptyList(),
                colors = emptyList()
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