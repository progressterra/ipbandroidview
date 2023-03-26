package com.progressterra.ipbandroidview.features.proshkacartcard

sealed class ProshkaCartCardEvent {

    object Open : ProshkaCartCardEvent()

    object RemoveFromCart : ProshkaCartCardEvent()
}