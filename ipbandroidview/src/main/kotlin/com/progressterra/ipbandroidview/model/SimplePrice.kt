package com.progressterra.ipbandroidview.model

import android.os.Parcelable
import com.progressterra.ipbandroidview.core.IsEmpty
import kotlinx.parcelize.Parcelize
import java.text.NumberFormat
import java.util.Locale

@Parcelize
data class SimplePrice(val price: Int = 0) : IsEmpty, Parcelable {

    override fun toString(): String =
        NumberFormat.getCurrencyInstance(Locale("ru", "RU")).format(price)

    override fun isEmpty(): Boolean = price == 0

    operator fun times(other: Int): SimplePrice = SimplePrice(price * other)
}
