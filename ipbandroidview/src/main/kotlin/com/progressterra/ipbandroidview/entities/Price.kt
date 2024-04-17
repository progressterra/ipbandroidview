package com.progressterra.ipbandroidview.entities

import android.os.Parcelable
import java.text.NumberFormat
import java.util.Locale
import kotlinx.parcelize.Parcelize

@Parcelize
data class Price(private val price: Int = 0) : IsEmpty, Parcelable {

    override fun toString(): String {
        val numberFormat = NumberFormat.getCurrencyInstance(Locale("ru", "RU"))
        numberFormat.maximumFractionDigits = 0
        return numberFormat.format(price)
    }

    fun toStringRaw() = price.toString()

    fun toDouble() = price.toDouble()

    fun isNegative(): Boolean = price < 0

    override fun isEmpty(): Boolean = price == 0

    operator fun times(other: Int): Price = Price(price * other)

    operator fun plus(other: Price) = Price(price + other.price)
}
