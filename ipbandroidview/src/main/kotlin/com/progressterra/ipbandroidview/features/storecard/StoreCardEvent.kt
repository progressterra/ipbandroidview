package com.progressterra.ipbandroidview.features.storecard

sealed class StoreCardEvent(val id: String) {

    class Open(id: String) : StoreCardEvent(id)

    class AddToCart(id: String) : StoreCardEvent(id)
}