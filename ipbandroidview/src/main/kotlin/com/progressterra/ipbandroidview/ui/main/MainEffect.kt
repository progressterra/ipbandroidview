package com.progressterra.ipbandroidview.ui.main

import androidx.annotation.StringRes
import com.progressterra.ipbandroidview.model.Goods

sealed class MainEffect {

    class GoodsDetails(val goods: Goods) : MainEffect()

    @Suppress("unused")
    class Toast(@StringRes val message: Int) : MainEffect()
}