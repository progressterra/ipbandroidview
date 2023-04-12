package com.progressterra.ipbandroidview.features.catalogcard

sealed class CatalogCardEvent(val id: String) {

    class Open(id: String) : CatalogCardEvent(id)
}