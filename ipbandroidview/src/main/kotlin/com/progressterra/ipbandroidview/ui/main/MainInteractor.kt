package com.progressterra.ipbandroidview.ui.main

import com.progressterra.ipbandroidview.composable.component.BonusesEvent
import com.progressterra.ipbandroidview.composable.component.StoreCardComponentState
import com.progressterra.ipbandroidview.composable.component.StoreCardInteractor
import com.progressterra.ipbandroidview.model.Category

interface MainInteractor : StoreCardInteractor {

    fun onCategory(category: Category)

    fun refresh()

    fun onBonusesEvent(id: String, event: BonusesEvent)

    class Empty : MainInteractor {

        override fun onBonusesEvent(id: String, event: BonusesEvent) = Unit

        override fun onClick(storeCard: StoreCardComponentState) = Unit

        override fun favorite(storeCard: StoreCardComponentState) = Unit

        override fun onCategory(category: Category) = Unit

        override fun refresh() = Unit
    }
}