package com.progressterra.ipbandroidview.ui.main

import com.progressterra.ipbandroidview.composable.component.ExtendedBonusesEvent
import com.progressterra.ipbandroidview.composable.component.StoreCardComponentState
import com.progressterra.ipbandroidview.composable.component.StoreCardInteractor
import com.progressterra.ipbandroidview.composable.component.UseExtendedBonuses
import com.progressterra.ipbandroidview.model.Category

interface MainInteractor : StoreCardInteractor, UseExtendedBonuses {

    fun onCategory(category: Category)

    fun refresh()

    class Empty : MainInteractor {

        override fun handleEvent(id: String, event: ExtendedBonusesEvent) = Unit

        override fun onClick(storeCard: StoreCardComponentState) = Unit

        override fun favorite(storeCard: StoreCardComponentState) = Unit

        override fun onCategory(category: Category) = Unit

        override fun refresh() = Unit
    }
}