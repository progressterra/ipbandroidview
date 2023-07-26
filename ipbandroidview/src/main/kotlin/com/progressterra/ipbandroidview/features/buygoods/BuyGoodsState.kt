package com.progressterra.ipbandroidview.features.buygoods

import arrow.optics.optics
import com.progressterra.ipbandroidview.entities.Installment
import com.progressterra.ipbandroidview.entities.SimplePrice
import com.progressterra.ipbandroidview.shared.ui.button.ButtonState

@optics
data class BuyGoodsState(
    val oldPrice: SimplePrice = SimplePrice(),
    val price: SimplePrice = SimplePrice(),
    val installment: Installment = Installment(),
    val buy: ButtonState = ButtonState(id = "buy"),
    val buyInstallment: ButtonState = ButtonState(id = "buyInstallment")
) {
    companion object
}
