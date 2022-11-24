package com.progressterra.ipbandroidview.ui.main

import androidx.annotation.StringRes

sealed class MainEffect {

    class Toast(@StringRes val message: Int) : MainEffect()

    object Bonuses : MainEffect()

    class Search(val keyword: String) : MainEffect()

    class GoodsDetails(val goodsId: String) : MainEffect()
}