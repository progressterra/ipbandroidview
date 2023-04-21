package com.progressterra.ipbandroidview.pages.goodsdetails

import com.progressterra.ipbandroidview.features.itemgallery.ItemGalleryEvent
import com.progressterra.ipbandroidview.features.itemgallery.UseItemGallery
import com.progressterra.ipbandroidview.features.topbar.TopBarEvent
import com.progressterra.ipbandroidview.features.topbar.UseTopBar

interface UseGoodsDetails : UseTopBar, UseItemGallery {

    class Empty : UseGoodsDetails {

        override fun handle(event: TopBarEvent) = Unit
        override fun handle(event: ItemGalleryEvent) = Unit
    }
}