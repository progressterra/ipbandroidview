package com.progressterra.ipbandroidview.features.receipt

import com.progressterra.ipbandroidview.entities.SimplePrice
import com.progressterra.ipbandroidview.shared.ui.button.ButtonState


data class ReceiptState(
    val total: SimplePrice = SimplePrice(),
    val items: List<Item> = emptyList(),
    val pay: ButtonState = ButtonState(id = "pay")
) {

    data class Item(
        val name: String,
        val price: SimplePrice,
        val quantity: Int
    )
}