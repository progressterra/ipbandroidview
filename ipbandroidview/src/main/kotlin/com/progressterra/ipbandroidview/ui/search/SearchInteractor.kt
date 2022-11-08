package com.progressterra.ipbandroidview.ui.search

import com.progressterra.ipbandroidview.actions.Back
import com.progressterra.ipbandroidview.actions.Favorite
import com.progressterra.ipbandroidview.actions.Refresh
import com.progressterra.ipbandroidview.actions.Search

interface SearchInteractor : Back, Refresh, Search, Favorite {

    fun keyword(keyword: String)
}