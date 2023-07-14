package com.progressterra.ipbandroidview.entities

import androidx.compose.runtime.Immutable

@Immutable
data class GoodsItem(
    override val id: String = "",
    val categoryId: String = "",
    val name: String = "",
    val price: SimplePrice = SimplePrice(),
    val imageUrl: String = "",
    val images: List<String> = emptyList(),
    val properties: List<Pair<String, String>> = emptyList(),
    val count: Int = 0,
    val description: String = "",
    val installment: Installment = Installment()
) : Id