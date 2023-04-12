package com.progressterra.ipbandroidview.features.catalogcard

sealed class CatalogCardEvent(val category: CatalogCardState) {

    class Open(category: CatalogCardState) : CatalogCardEvent(category)
}