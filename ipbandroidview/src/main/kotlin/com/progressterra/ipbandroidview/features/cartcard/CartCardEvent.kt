package com.progressterra.ipbandroidview.features.cartcard

sealed class CartCardEvent(val id: String) {

    class Open(id: String) : CartCardEvent(id)

    class RemoveFromCart(id: String) : CartCardEvent(id)
}