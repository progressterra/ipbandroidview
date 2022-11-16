package com.progressterra.ipbandroidview.domain.mapper

import com.progressterra.ipbandroidview.core.Mapper
import com.progressterra.ipbandroidview.model.SimplePrice

interface PriceMapper : Mapper<Double, SimplePrice> {

    class Russia : PriceMapper {

        override fun map(data: Double): SimplePrice = SimplePrice(
            "${data.toInt()} ₽", data.toInt()
        )
    }
}