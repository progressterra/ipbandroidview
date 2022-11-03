package com.progressterra.ipbandroidview.dto

data class Goods(
    val id: String,
    val name: String,
    val description: String,
    val price: String,
    val favorite: Boolean,
    val images: List<String>,
    val parameters: List<GoodsParameters>,
    val countInCart: String
)