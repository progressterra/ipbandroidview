package com.progressterra.ipbandroidview.entities

import android.os.Parcelable
import com.progressterra.ipbandroidview.shared.IsEmpty
import com.progressterra.ipbandroidview.shared.IsNegative
import kotlinx.parcelize.Parcelize
import java.text.NumberFormat
import java.util.Locale

@Parcelize
data class SimplePrice(private val price: Int = 0) : IsEmpty, Parcelable, IsNegative {

    override fun toString(): String {
        val numberFormat = NumberFormat.getCurrencyInstance(Locale("ru", "RU"))
        numberFormat.maximumFractionDigits = 0
        return numberFormat.format(price)
    }

    fun toStringRaw() = price.toString()

    fun toDouble() = price.toDouble()

    override fun isNegative(): Boolean = price < 0

    override fun isEmpty(): Boolean = price == 0

    operator fun times(other: Int): SimplePrice = SimplePrice(price * other)

    operator fun plus(other: SimplePrice) = SimplePrice(price + other.price)
}
