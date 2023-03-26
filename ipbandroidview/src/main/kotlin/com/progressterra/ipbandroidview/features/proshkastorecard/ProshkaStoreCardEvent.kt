package com.progressterra.ipbandroidview.features.proshkastorecard

sealed class ProshkaStoreCardEvent {

    object Open : ProshkaStoreCardEvent()

    object AddToCart : ProshkaStoreCardEvent()
}