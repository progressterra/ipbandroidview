package com.progressterra.ipbandroidview.features.buygoods

sealed class BuyGoodsEvent(val id: String) {

    class BuyWithLoan(id: String) : BuyGoodsEvent(id)

    class Buy(id: String) : BuyGoodsEvent(id)
}
