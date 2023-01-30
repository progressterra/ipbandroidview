package com.progressterra.ipbandroidview.domain.mapper

import com.progressterra.ipbandroidapi.api.product.models.ColorData
import com.progressterra.ipbandroidview.R
import com.progressterra.ipbandroidview.core.ManageResources
import com.progressterra.ipbandroidview.core.Mapper
import com.progressterra.ipbandroidview.model.store.GoodsColor

interface GoodsColorMapper : Mapper<ColorData, GoodsColor> {

    class Base(
        manageResources: ManageResources
    ) : GoodsColorMapper {

        private val noData = manageResources.string(R.string.no_data)

        override fun map(data: ColorData): GoodsColor = GoodsColor(
            image = data.url ?: "", hex = data.hexCode ?: "", name = data.colorName ?: noData
        )
    }
}