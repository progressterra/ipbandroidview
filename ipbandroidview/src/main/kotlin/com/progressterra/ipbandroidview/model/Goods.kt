package com.progressterra.ipbandroidview.model

import com.progressterra.ipbandroidview.components.StoreCardState
import com.progressterra.ipbandroidview.model.component.Id

interface StoreGoods : StoreCardState,
    Id {


    class Base(
        override val id: String,
        override val name: String,
        override val image: String,
        override val price: String,
        override val favorite: Boolean
    ) : StoreGoods
}