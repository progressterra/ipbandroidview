package com.progressterra.ipbandroidview.features.receipt

import androidx.compose.runtime.Immutable
import com.progressterra.ipbandroidview.entities.SimplePrice
import com.progressterra.ipbandroidview.shared.ui.button.ButtonState
import com.progressterra.processors.IpbSubState

@Immutable
data class ReceiptState(
    val total: SimplePrice = SimplePrice(),
    val items: List<Item> = emptyList(),
    @IpbSubState val pay: ButtonState = ButtonState(id = "pay")
) {

    data class Item(
        val name: String,
        val price: SimplePrice,
        val quantity: Int
    )
}