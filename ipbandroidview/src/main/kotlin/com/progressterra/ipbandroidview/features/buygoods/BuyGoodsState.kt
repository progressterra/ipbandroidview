package com.progressterra.ipbandroidview.features.buygoods

import androidx.compose.runtime.Immutable
import com.progressterra.ipbandroidview.entities.SimplePrice
import com.progressterra.ipbandroidview.shared.ui.button.ButtonState

@Immutable
data class BuyGoodsState(
    val price: SimplePrice = SimplePrice(),
    val loan: String = "",
    val buy: ButtonState = ButtonState(
        id = "buy"
    ),
    val buyWithLoan: ButtonState = ButtonState(
        id = "buyWithLoan"
    )
)
