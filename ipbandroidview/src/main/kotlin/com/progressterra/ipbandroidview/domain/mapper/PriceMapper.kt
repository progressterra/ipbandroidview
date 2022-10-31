package com.progressterra.ipbandroidview.domain.mapper

import com.progressterra.ipbandroidview.core.Mapper
import java.text.NumberFormat
import java.util.Currency

interface PriceMapper : Mapper<Double, String> {

    class Russia : PriceMapper {

        override fun map(data: Double): String {
            val format: NumberFormat = NumberFormat.getCurrencyInstance()
            format.maximumFractionDigits = 0
            format.currency = Currency.getInstance("RUB")
            return format.format(
                data
            )
        }
    }
}