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

    fun toOrderGoods(): OrderGoods = OrderGoods(
        id = this.id,
        inCartCounter = this.inCartCounter,
        image = this.image,
        name = name,
        totalPrice = price * inCartCounter
    )
}