package com.progressterra.ipbandroidview.features.proshkaoffer

sealed class ProshkaOfferEvent(val id: String) {

    class Clicked(id: String) : ProshkaOfferEvent(id)
}