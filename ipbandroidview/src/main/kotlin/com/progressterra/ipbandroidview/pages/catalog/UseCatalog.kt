package com.progressterra.ipbandroidview.pages.catalog

import com.progressterra.ipbandroidview.features.search.UseSearch
import com.progressterra.ipbandroidview.features.trace.UseTrace
import com.progressterra.ipbandroidview.shared.ui.statebox.UseStateBox
import com.progressterra.ipbandroidview.widgets.catalogitems.UseCatalogItems
import com.progressterra.ipbandroidview.widgets.storeitems.UseStoreItems

interface UseCatalog : UseSearch, UseCatalogItems, UseStateBox, UseStoreItems, UseTrace