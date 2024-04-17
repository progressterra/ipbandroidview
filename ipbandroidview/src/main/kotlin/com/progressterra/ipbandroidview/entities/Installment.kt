package com.progressterra.ipbandroidview.entities

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class Installment(val months: Int = 0, val perMonth: Price = Price()) : IsEmpty, Parcelable {

    override fun isEmpty(): Boolean = months == 0 && perMonth == Price()
}
