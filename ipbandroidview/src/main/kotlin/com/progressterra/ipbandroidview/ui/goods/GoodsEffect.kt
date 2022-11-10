package com.progressterra.ipbandroidview.ui.goods

import com.progressterra.ipbandroidview.model.Goods


sealed class GoodsEffect {

    object Back : GoodsEffect()

    class GoodsDetails(val goods: Goods) : GoodsEffect()
}