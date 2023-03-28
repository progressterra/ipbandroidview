package com.progressterra.ipbandroidview.processes.mapper

import com.progressterra.ipbandroidview.core.Mapper
import com.progressterra.ipbandroidview.entities.SimplePrice

interface PriceMapper : Mapper<Double, SimplePrice> {

    class Russia : PriceMapper {

        override fun map(data: Double): SimplePrice = SimplePrice(data.toInt())
    }
}