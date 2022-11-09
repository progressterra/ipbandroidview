package com.progressterra.ipbandroidview.ui.search

import com.progressterra.ipbandroidview.actions.Back
import com.progressterra.ipbandroidview.actions.Favorite
import com.progressterra.ipbandroidview.actions.Refresh
import com.progressterra.ipbandroidview.actions.Search

interface SearchInteractor : Back, Refresh, Search, Favorite {

    fun keyword(keyword: String)

    fun goodsDetails(goodsId: String)

    class Empty : SearchInteractor {

        override fun back() = Unit

        override fun favorite(goodsId: String, favorite: Boolean) = Unit

        override fun refresh() = Unit

        override fun search() = Unit

        override fun keyword(keyword: String) = Unit

        override fun goodsDetails(goodsId: String) = Unit
    }
}