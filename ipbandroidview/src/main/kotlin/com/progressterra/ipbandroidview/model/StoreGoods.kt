package com.progressterra.ipbandroidview.model

import androidx.compose.runtime.Immutable
import com.progressterra.ipbandroidview.components.StoreCardState
import com.progressterra.ipbandroidview.model.component.Id

@Immutable
interface StoreGoods : StoreCardState,
    Id {

    fun reverseFavorite(): StoreGoods

    data class Base(
        override val id: String,
        override val name: String,
        override val image: String,
        override val price: SimplePrice,
        override val favorite: Boolean
    ) : StoreGoods {

        override fun reverseFavorite(): StoreGoods = this.copy(favorite = !favorite)
    }
}