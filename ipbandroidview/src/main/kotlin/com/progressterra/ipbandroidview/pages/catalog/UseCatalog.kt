package com.progressterra.ipbandroidview.pages.catalog

import com.progressterra.ipbandroidview.features.catalogcard.CatalogCardEvent
import com.progressterra.ipbandroidview.features.catalogcard.UseCatalogCard
import com.progressterra.ipbandroidview.features.search.SearchEvent
import com.progressterra.ipbandroidview.features.search.UseSearch
import com.progressterra.ipbandroidview.features.storecard.StoreCardEvent
import com.progressterra.ipbandroidview.features.trace.TraceEvent
import com.progressterra.ipbandroidview.features.trace.UseTrace
import com.progressterra.ipbandroidview.shared.ui.counter.CounterEvent
import com.progressterra.ipbandroidview.shared.ui.statecolumn.StateColumnEvent
import com.progressterra.ipbandroidview.shared.ui.statecolumn.UseStateColumn
import com.progressterra.ipbandroidview.widgets.storeitems.UseStoreItems

interface UseCatalog : UseSearch, UseCatalogCard, UseStateColumn, UseStoreItems, UseTrace {

    class Empty : UseCatalog {

        override fun handle(event: CatalogCardEvent) = Unit

        override fun handle(event: SearchEvent) = Unit

        override fun handle(event: StoreCardEvent) = Unit

        override fun handle(event: TraceEvent) = Unit

        override fun handle(event: CounterEvent) = Unit

        override fun handle(event: StateColumnEvent) = Unit
    }
}