package com.progressterra.ipbandroidview.model.store

import androidx.compose.runtime.Immutable

@Immutable
data class GoodsDetails(
    val color: String = "",
    val description: String = "",
    val favorite: Boolean = false,
    val images: List<String> = emptyList(),
    val inCartCounter: Int = 0,
    val name: String = "",
    val parameters: List<GoodsParameters> = emptyList(),
    val price: SimplePrice = SimplePrice(),
    val size: GoodsSize = GoodsSize(available = false, primary = "", secondary = null),
    val sizes: List<GoodsSize> = emptyList()
) {

    fun addOne(): GoodsDetails = this.copy(inCartCounter = inCartCounter + 1)

    fun removeOne(): GoodsDetails = this.copy(inCartCounter = inCartCounter - 1)

    fun reverseFavorite(): GoodsDetails = this.copy(favorite = !favorite)
}
