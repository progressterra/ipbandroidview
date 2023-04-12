package com.progressterra.ipbandroidview.features.proshkacartcard

sealed class ProshkaCartCardEvent(val id: String) {

    class Open(id: String) : ProshkaCartCardEvent(id)

    class RemoveFromCart(id: String) : ProshkaCartCardEvent(id)
}