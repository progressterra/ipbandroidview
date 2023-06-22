package com.progressterra.ipbandroidview.features.buygoods

sealed class BuyGoodsEvent(val id: String) {

    class BuyInstallment(id: String) : BuyGoodsEvent(id)

    class Buy(id: String) : BuyGoodsEvent(id)
}
