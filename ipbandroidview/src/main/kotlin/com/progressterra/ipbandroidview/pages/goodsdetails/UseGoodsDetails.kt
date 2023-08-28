package com.progressterra.ipbandroidview.pages.goodsdetails

import com.progressterra.ipbandroidview.features.buygoods.UseBuyGoods
import com.progressterra.ipbandroidview.features.favoritebutton.FavoriteButtonEvent
import com.progressterra.ipbandroidview.features.goodsdescription.GoodsDescriptionEvent
import com.progressterra.ipbandroidview.features.goodsdescription.UseGoodsDescription
import com.progressterra.ipbandroidview.features.itemgallery.ItemGalleryEvent
import com.progressterra.ipbandroidview.features.itemgallery.UseItemGallery
import com.progressterra.ipbandroidview.features.storecard.StoreCardEvent
import com.progressterra.ipbandroidview.features.topbar.TopBarEvent
import com.progressterra.ipbandroidview.features.topbar.UseTopBar
import com.progressterra.ipbandroidview.shared.ui.button.ButtonEvent
import com.progressterra.ipbandroidview.shared.ui.counter.CounterEvent
import com.progressterra.ipbandroidview.shared.ui.statecolumn.StateColumnEvent
import com.progressterra.ipbandroidview.shared.ui.statecolumn.UseStateColumn
import com.progressterra.ipbandroidview.widgets.galleries.UseGalleries

interface UseGoodsDetails : UseTopBar, UseItemGallery, UseGoodsDescription, UseBuyGoods,
    UseStateColumn, UseGalleries {

    class Empty : UseGoodsDetails {

        override fun handle(event: GoodsDescriptionEvent) = Unit

        override fun handle(event: StateColumnEvent) = Unit

        override fun handle(event: TopBarEvent) = Unit

        override fun handle(event: ItemGalleryEvent) = Unit

        override fun handle(event: FavoriteButtonEvent) = Unit

        override fun handle(event: StoreCardEvent) = Unit

        override fun handle(event: ButtonEvent) = Unit

        override fun handle(event: CounterEvent) = Unit
    }
}