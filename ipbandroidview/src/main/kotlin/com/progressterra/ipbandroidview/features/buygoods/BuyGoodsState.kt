package com.progressterra.ipbandroidview.features.buygoods

import androidx.compose.runtime.Immutable
import com.progressterra.ipbandroidview.entities.Installment
import com.progressterra.ipbandroidview.entities.SimplePrice
import com.progressterra.ipbandroidview.shared.ui.button.ButtonState
import com.progressterra.processors.IpbSubState

@Immutable
data class BuyGoodsState(
    val price: SimplePrice = SimplePrice(),
    val installment: Installment = Installment(),
    @IpbSubState val buy: ButtonState = ButtonState(
        id = "buy"
    ),
    @IpbSubState val buyWithLoan: ButtonState = ButtonState(
        id = "buyWithLoan"
    )
)
