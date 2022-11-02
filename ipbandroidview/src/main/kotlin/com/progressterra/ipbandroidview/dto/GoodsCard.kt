package com.progressterra.ipbandroidview.dto

data class GoodsCard(
    val id: String,
    val images: List<String>,
    val price: String,
    val name: String,
    val favorite: Boolean
)