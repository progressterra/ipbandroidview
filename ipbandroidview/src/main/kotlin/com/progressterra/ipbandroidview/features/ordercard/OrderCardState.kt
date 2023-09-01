package com.progressterra.ipbandroidview.features.ordercard

import android.os.Parcelable
import com.progressterra.ipbandroidview.entities.Installment
import com.progressterra.ipbandroidview.entities.SimplePrice
import kotlinx.parcelize.Parcelize


@Parcelize
data class OrderCardState(
    val id: String = "",
    val name: String = "",
    val oldPrice: SimplePrice = SimplePrice(),
    val price: SimplePrice = SimplePrice(),
    val image: String = "",
    val installment: Installment = Installment(),
    val count: Int = 0,
    val properties: List<Pair<String, String>> = emptyList()
) : Parcelable
