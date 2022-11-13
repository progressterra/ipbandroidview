package com.progressterra.ipbandroidview.ui.main

import androidx.annotation.StringRes

@Suppress("unused")
sealed class MainEffect {

    class GoodsDetails(val goodsId: String) : MainEffect()

    class Toast(@StringRes val message: Int) : MainEffect()
}