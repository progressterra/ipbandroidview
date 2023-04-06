package com.progressterra.ipbandroidview.processes.mapper

import com.progressterra.ipbandroidview.shared.Mapper
import com.progressterra.ipbandroidview.entities.SimplePrice

interface PriceMapper : Mapper<Double, SimplePrice> {

    class Russia : PriceMapper {

        override fun map(data: Double): SimplePrice = SimplePrice(data.toInt())
    }
}