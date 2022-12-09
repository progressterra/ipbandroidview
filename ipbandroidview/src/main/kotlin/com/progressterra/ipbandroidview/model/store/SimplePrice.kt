package com.progressterra.ipbandroidview.model.store

import android.os.Parcelable
import com.progressterra.ipbandroidview.core.IsEmpty
import kotlinx.parcelize.Parcelize
import java.text.NumberFormat
import java.util.Locale

@Parcelize
data class SimplePrice(val price: Int = 0) : IsEmpty, Parcelable {

    override fun toString(): String {
        val numberFormat = NumberFormat.getCurrencyInstance(Locale("ru", "RU"))
        numberFormat.maximumFractionDigits = 0
        return numberFormat.format(price)
    }

    override fun isEmpty(): Boolean = price == 0

    operator fun times(other: Int): SimplePrice = SimplePrice(price * other)

    operator fun plus(other: SimplePrice) = SimplePrice(price + other.price)
}
