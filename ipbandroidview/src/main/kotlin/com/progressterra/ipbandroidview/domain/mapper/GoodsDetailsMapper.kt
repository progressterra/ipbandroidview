package com.progressterra.ipbandroidview.domain.mapper

import com.google.gson.Gson
import com.google.gson.annotations.SerializedName
import com.progressterra.ipbandroidapi.api.models.RGGoodsInventoryExt
import com.progressterra.ipbandroidview.R
import com.progressterra.ipbandroidview.core.AbstractMapper
import com.progressterra.ipbandroidview.core.ManageResources
import com.progressterra.ipbandroidview.model.store.GoodsColor
import com.progressterra.ipbandroidview.model.store.GoodsDetails
import com.progressterra.ipbandroidview.model.store.GoodsParameters
import com.progressterra.ipbandroidview.model.store.GoodsSize
import com.progressterra.ipbandroidview.model.store.SimplePrice

interface GoodsDetailsMapper {

    fun map(
        goodsRaw: RGGoodsInventoryExt,
        isFavorite: Boolean,
        count: Int,
        colors: List<GoodsColor>,
        sizes: List<GoodsSize>,
        sizeTableUrl: String
    ): GoodsDetails

    class Base(
        gson: Gson, manageResources: ManageResources, private val priceMapper: PriceMapper
    ) : GoodsDetailsMapper, AbstractMapper(gson) {

        private val noData = manageResources.string(R.string.no_data)

        override fun map(
            goodsRaw: RGGoodsInventoryExt,
            isFavorite: Boolean,
            count: Int,
            colors: List<GoodsColor>,
            sizes: List<GoodsSize>,
            sizeTableUrl: String
        ): GoodsDetails {
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
            return GoodsDetails(
                images = images.map { it.url },
                price = goodsRaw.currentPrice?.let { priceMapper.map(it) } ?: SimplePrice(),
                name = goodsRaw.name ?: noData,
                favorite = isFavorite,
                description = goodsRaw.extendedDescription ?: noData,
                parameters = parameters,
                inCartCounter = count,
                color = colors.firstOrNull() ?: GoodsColor(),
                size = sizes.firstOrNull() ?: GoodsSize(),
                sizes = sizes,
                colors = colors,
                sizeTableUrl = sizeTableUrl
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