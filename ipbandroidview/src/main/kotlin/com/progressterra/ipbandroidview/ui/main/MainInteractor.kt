package com.progressterra.ipbandroidview.ui.main

import com.progressterra.ipbandroidview.features.proshkabonuses.ProshkaBonusesEvent
import com.progressterra.ipbandroidview.composable.component.StoreCardComponentState
import com.progressterra.ipbandroidview.composable.component.StoreCardInteractor
import com.progressterra.ipbandroidview.features.proshkabonuses.UseProshkaBonuses
import com.progressterra.ipbandroidview.model.Category

interface MainInteractor : StoreCardInteractor, UseProshkaBonuses {

    fun onCategory(category: Category)

    fun refresh()

    class Empty : MainInteractor {

        override fun handle(id: String, event: ProshkaBonusesEvent) = Unit

        override fun onClick(storeCard: StoreCardComponentState) = Unit

        override fun favorite(storeCard: StoreCardComponentState) = Unit

        override fun onCategory(category: Category) = Unit

        override fun refresh() = Unit
    }
}