package com.progressterra.ipbandroidview.model

import com.progressterra.ipbandroidview.model.component.Price

data class Cart(
    val listGoods: List<CartGoods> = emptyList(),
    override val price: String = ""
) : Price