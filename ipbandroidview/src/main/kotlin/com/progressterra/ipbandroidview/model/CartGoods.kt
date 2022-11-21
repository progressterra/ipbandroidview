package com.progressterra.ipbandroidview.model

data class CartGoods(
    override val id: String,
    val color: GoodsColor,
    val favorite: Boolean,
    val image: String,
    val inCartCounter: Int,
    val name: String,
    val price: SimplePrice,
    val size: GoodsSize
) : Id {

    fun reverseFavorite(): CartGoods = this.copy(favorite = !favorite)
}