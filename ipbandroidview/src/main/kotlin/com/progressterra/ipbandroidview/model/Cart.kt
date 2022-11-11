package com.progressterra.ipbandroidview.model

import com.progressterra.ipbandroidview.model.component.Price

interface Cart : Price {

    val listGoods: List<CartGoods>

    class Base(
        override val listGoods: List<CartGoods> = emptyList(),
        override val price: String = ""
    ) : Cart
}