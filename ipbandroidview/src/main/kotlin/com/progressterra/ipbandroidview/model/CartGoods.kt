package com.progressterra.ipbandroidview.model

import com.progressterra.ipbandroidview.components.CartCardState
import com.progressterra.ipbandroidview.model.component.Id

interface CartGoods : CartCardState, Id {

    fun reverseFavorite(): CartGoods

    data class Base(
        override val id: String,
        override val color: GoodsColor,
        override val favorite: Boolean,
        override val image: String,
        override val inCartCounter: Int,
        override val name: String,
        override val price: SimplePrice,
        override val size: GoodsSize
    ) : CartGoods {

        override fun reverseFavorite(): CartGoods = this.copy(favorite = !favorite)
    }
}