package com.progressterra.ipbandroidview.model

data class StoreGoods(
    override val id: String,
    val name: String,
    val image: String,
    val price: SimplePrice,
    val favorite: Boolean
) : Id {

    fun reverseFavorite(): StoreGoods = this.copy(favorite = !favorite)
}