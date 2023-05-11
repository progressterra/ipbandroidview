package com.progressterra.ipbandroidview.ui.goodsdetails

sealed class GoodsDetailsEffect {

    object Back : GoodsDetailsEffect()

    object Next : GoodsDetailsEffect()

    object Auth : GoodsDetailsEffect()
}