package com.progressterra.ipbandroidview.features.storecardwide

sealed class StoreCardWideEvent(val id: String) {

    class Open(id: String) : StoreCardWideEvent(id)

    class AddToCart(id: String) : StoreCardWideEvent(id)
}
