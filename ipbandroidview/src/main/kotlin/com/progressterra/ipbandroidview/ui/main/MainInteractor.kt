package com.progressterra.ipbandroidview.ui.main

import com.progressterra.ipbandroidview.model.Category
import com.progressterra.ipbandroidview.composable.component.StoreCardComponentState
import com.progressterra.ipbandroidview.composable.component.StoreCardInteractor

interface MainInteractor : StoreCardInteractor {

    fun onCategory(category: Category)

    fun refresh()

    class Empty : MainInteractor {

        override fun onClick(storeCard: StoreCardComponentState) = Unit

        override fun favorite(storeCard: StoreCardComponentState) = Unit

        override fun onCategory(category: Category) = Unit

        override fun refresh() = Unit
    }
}