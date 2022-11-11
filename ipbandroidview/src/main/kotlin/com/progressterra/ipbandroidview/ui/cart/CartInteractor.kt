package com.progressterra.ipbandroidview.ui.cart

import com.progressterra.ipbandroidview.actions.Details
import com.progressterra.ipbandroidview.actions.FavoriteSpecific
import com.progressterra.ipbandroidview.actions.Next
import com.progressterra.ipbandroidview.actions.Refresh
import com.progressterra.ipbandroidview.actions.RemoveSpecific
import com.progressterra.ipbandroidview.model.Goods

interface CartInteractor : Refresh,
    FavoriteSpecific<Goods>,
    RemoveSpecific<Goods>,
    Next,
    Details<Goods> {

    fun auth()

    class Empty : CartInteractor {

        override fun openDetails(item: Goods) = Unit

        override fun favoriteSpecific(item: Goods) = Unit

        override fun next() = Unit

        override fun refresh() = Unit

        override fun removeSpecific(item: Goods) = Unit

        override fun auth() = Unit
    }
}