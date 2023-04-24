package com.progressterra.ipbandroidview.pages.catalog

import com.progressterra.ipbandroidview.features.catalogcard.CatalogCardEvent
import com.progressterra.ipbandroidview.features.search.SearchEvent
import com.progressterra.ipbandroidview.features.search.UseSearch
import com.progressterra.ipbandroidview.features.storecard.StoreCardEvent
import com.progressterra.ipbandroidview.features.trace.TraceEvent
import com.progressterra.ipbandroidview.features.trace.UseTrace
import com.progressterra.ipbandroidview.shared.ui.counter.CounterEvent
import com.progressterra.ipbandroidview.shared.ui.statebox.StateBoxEvent
import com.progressterra.ipbandroidview.shared.ui.statebox.UseStateBox
import com.progressterra.ipbandroidview.widgets.catalogitems.UseCatalogItems
import com.progressterra.ipbandroidview.widgets.storeitems.UseStoreItems

interface UseCatalog : UseSearch, UseCatalogItems, UseStateBox, UseStoreItems, UseTrace {

    class Empty : UseCatalog {

        override fun handle(event: CatalogCardEvent) = Unit

        override fun handle(event: SearchEvent) = Unit

        override fun handle(event: StoreCardEvent) = Unit

        override fun handle(event: TraceEvent) = Unit

        override fun handle(event: CounterEvent) = Unit

        override fun handle(event: StateBoxEvent) = Unit
    }
}