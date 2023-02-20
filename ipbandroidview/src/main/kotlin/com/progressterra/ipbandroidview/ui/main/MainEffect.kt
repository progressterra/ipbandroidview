package com.progressterra.ipbandroidview.ui.main

import androidx.annotation.StringRes

sealed class MainEffect {

    class Toast(@StringRes val message: Int) : MainEffect()

    object Bonuses : MainEffect()

    object Invite : MainEffect()

    object Spend : MainEffect()

    class GoodsDetails(val goodsId: String) : MainEffect()

    class Goods(val categoryId: String) : MainEffect()
}