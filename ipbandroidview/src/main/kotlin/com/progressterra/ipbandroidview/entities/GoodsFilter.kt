package com.progressterra.ipbandroidview.entities

data class GoodsFilter(
    val categoryId: String? = null,
    val search: String? = null,
    val params: Map<String, List<String>>? = null,
)