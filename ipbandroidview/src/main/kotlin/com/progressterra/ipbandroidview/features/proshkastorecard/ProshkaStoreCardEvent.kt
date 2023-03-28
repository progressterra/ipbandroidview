package com.progressterra.ipbandroidview.features.proshkastorecard

sealed class ProshkaStoreCardEvent(val id: String) {

    class Open(id: String) : ProshkaStoreCardEvent(id)

    class AddToCart(id: String) : ProshkaStoreCardEvent(id)
}