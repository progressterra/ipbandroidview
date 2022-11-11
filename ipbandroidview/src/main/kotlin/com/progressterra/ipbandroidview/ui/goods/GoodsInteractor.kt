package com.progressterra.ipbandroidview.ui.goods

import com.progressterra.ipbandroidview.actions.Back
import com.progressterra.ipbandroidview.actions.Details
import com.progressterra.ipbandroidview.actions.FavoriteSpecific
import com.progressterra.ipbandroidview.actions.Refresh
import com.progressterra.ipbandroidview.model.Goods

interface GoodsInteractor : Refresh, Back, FavoriteSpecific<Goods>, Details<Goods> {

    class Empty : GoodsInteractor {

        override fun back() = Unit

        override fun favoriteSpecific(item: Goods) = Unit

        override fun refresh() = Unit

        override fun openDetails(item: Goods) = Unit
    }
}