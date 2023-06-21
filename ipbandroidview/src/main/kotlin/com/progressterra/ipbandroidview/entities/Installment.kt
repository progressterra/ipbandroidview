package com.progressterra.ipbandroidview.entities

import android.os.Parcelable
import androidx.compose.runtime.Immutable
import com.progressterra.ipbandroidview.shared.IsEmpty
import kotlinx.parcelize.Parcelize

@Parcelize
@Immutable
data class Installment(
    val months: Int = 0,
    val perMonth: SimplePrice = SimplePrice()
) : IsEmpty, Parcelable {

    override fun isEmpty(): Boolean = months == 0 && perMonth == SimplePrice()
}
