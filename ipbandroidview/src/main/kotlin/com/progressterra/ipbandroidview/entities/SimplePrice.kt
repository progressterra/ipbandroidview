package com.progressterra.ipbandroidview.entities

import android.os.Parcelable
import com.progressterra.ipbandroidview.core.IsEmpty
import kotlinx.parcelize.Parcelize
import java.text.NumberFormat
import java.util.Locale

@Parcelize
class SimplePrice(private val price: Int = 0) : IsEmpty, Parcelable {

    constructor(price: Double) : this(price.toInt())

    override fun toString(): String {
        val numberFormat = NumberFormat.getCurrencyInstance(Locale("ru", "RU"))
        numberFormat.maximumFractionDigits = 0
        return numberFormat.format(price)
    }

    override fun isEmpty(): Boolean = price == 0

    operator fun times(other: Int): SimplePrice = SimplePrice(price * other)

    operator fun plus(other: SimplePrice) = SimplePrice(price + other.price)
}
