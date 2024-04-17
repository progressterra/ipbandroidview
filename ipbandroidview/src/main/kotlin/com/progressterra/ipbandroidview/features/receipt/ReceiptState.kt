package com.progressterra.ipbandroidview.features.receipt

import com.progressterra.ipbandroidview.entities.Price
import com.progressterra.ipbandroidview.shared.ui.button.ButtonState

data class ReceiptState(
    val total: Price = Price(),
    val items: List<Item> = emptyList(),
    val pay: ButtonState = ButtonState(id = "pay")
) {

    data class Item(val name: String, val price: Price, val quantity: Int)
}
