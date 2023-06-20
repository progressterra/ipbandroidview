package com.progressterra.ipbandroidview.entities

import com.progressterra.ipbandroidview.shared.IsEmpty

data class Installment(
    val months: Int = 0,
    val perMonth: SimplePrice = SimplePrice()
) : IsEmpty {

    override fun isEmpty(): Boolean = months == 0 && perMonth == SimplePrice()
}
