package com.progressterra.ipbandroidview.model

import com.progressterra.ipbandroidview.components.CartCardState
import com.progressterra.ipbandroidview.model.component.Id

interface CartGoods : CartCardState, Id {

    class Base(
        override val id: String,
        override val color: GoodsColor,
        override val favorite: Boolean,
        override val image: String,
        override val inCartCounter: Int,
        override val name: String,
        override val price: String,
        override val size: GoodsSize
    ) : CartGoods
}