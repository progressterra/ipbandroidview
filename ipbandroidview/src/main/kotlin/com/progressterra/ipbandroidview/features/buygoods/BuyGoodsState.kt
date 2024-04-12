package com.progressterra.ipbandroidview.features.buygoods

import com.progressterra.ipbandroidview.entities.Installment
import com.progressterra.ipbandroidview.entities.Price
import com.progressterra.ipbandroidview.shared.ui.button.ButtonState

data class BuyGoodsState(
    val oldPrice: Price = Price(),
    val price: Price = Price(),
    val installment: Installment = Installment(),
    val buy: ButtonState = ButtonState(id = "buy"),
    val buyInstallment: ButtonState = ButtonState(id = "buyInstallment")
) {
    companion object
}
