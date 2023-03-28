package com.progressterra.ipbandroidview.features.proshkacatalogcard

sealed class ProshkaCatalogCardEvent(val id: String) {

    class Open(id: String) : ProshkaCatalogCardEvent(id)
}