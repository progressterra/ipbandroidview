package com.progressterra.ipbandroidview.domain.mapper

import com.progressterra.ipbandroidview.core.Mapper

interface PriceMapper : Mapper<Double, String> {

    class Russia : PriceMapper {

        override fun map(data: Double): String =
            "${data.toInt()} ₽"
    }
}