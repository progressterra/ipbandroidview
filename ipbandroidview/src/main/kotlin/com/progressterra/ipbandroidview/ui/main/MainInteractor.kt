package com.progressterra.ipbandroidview.ui.main

import com.progressterra.ipbandroidview.actions.Back
import com.progressterra.ipbandroidview.actions.Favorite
import com.progressterra.ipbandroidview.actions.Refresh

interface MainInteractor : Refresh, Back, Favorite {

    fun keyword(keyword: String)

    fun card(id: String)

    fun search()

    class Empty : MainInteractor {

        override fun favorite(id: String) = Unit

        override fun card(id: String) = Unit

        override fun search() = Unit

        override fun back() = Unit

        override fun refresh() = Unit

        override fun keyword(keyword: String) = Unit
    }
}