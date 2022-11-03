package com.progressterra.ipbandroidview.dto

sealed class GoodsDetails {

    data class Description(
        val name: String,
        val description: String
    ) : GoodsDetails()

    data class Parameters(
        val parameters: List<Item>
    ) : GoodsDetails() {

        data class Item(
            val name: String,
            val value: String
        )
    }

    data class Delivery(
        val placeholder: String
    ) : GoodsDetails()
}
