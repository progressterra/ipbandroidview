package com.progressterra.ipbandroidview.pages.catalog


sealed class CatalogEvent {

    class OnItem(val id: String) : CatalogEvent()
}
