package com.progressterra.ipbandroidview.pages.goodsdetails

import com.progressterra.ipbandroidview.features.favoritebutton.FavoriteButtonEvent
import com.progressterra.ipbandroidview.features.goodsdescription.GoodsDescriptionEvent
import com.progressterra.ipbandroidview.features.goodsdescription.UseGoodsDescription
import com.progressterra.ipbandroidview.features.itemgallery.ItemGalleryEvent
import com.progressterra.ipbandroidview.features.itemgallery.UseItemGallery
import com.progressterra.ipbandroidview.features.topbar.TopBarEvent
import com.progressterra.ipbandroidview.features.topbar.UseTopBar

interface UseGoodsDetails : UseTopBar, UseItemGallery, UseGoodsDescription {

    class Empty : UseGoodsDetails {

        override fun handle(event: TopBarEvent) = Unit
        override fun handle(event: ItemGalleryEvent) = Unit

        override fun handle(event: FavoriteButtonEvent) = Unit

        override fun handle(event: GoodsDescriptionEvent) = Unit
    }
}