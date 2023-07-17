package com.progressterra.ipbandroidview.features.goodsdescription

import com.progressterra.ipbandroidview.features.favoritebutton.FavoriteButtonEvent
import com.progressterra.ipbandroidview.features.favoritebutton.UseFavoriteButton

interface UseGoodsDescription : UseFavoriteButton {

    class Empty : UseGoodsDescription {

        override fun handle(event: FavoriteButtonEvent) = Unit
    }
}
