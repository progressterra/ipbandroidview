package com.progressterra.ipbandroidview.features.ordercard

import android.os.Parcelable
import androidx.compose.runtime.Immutable
import com.progressterra.ipbandroidview.entities.Installment
import com.progressterra.ipbandroidview.entities.SimplePrice
import kotlinx.parcelize.Parcelize

@Immutable
@Parcelize
data class OrderCardState(
    val id: String = "",
    val name: String = "",
    val price: SimplePrice = SimplePrice(),
    val imageUrl: String = "",
    val installment: Installment = Installment(),
    val count: Int = 0,
    val properties: Map<String, String> = emptyMap()
) : Parcelable
