package com.progressterra.ipbandroidview.composable.linktext

data class LinkTextData(
    val text: String,
    val tag: String? = null,
    val annotation: String? = null,
    val onClick: ((String) -> Unit)? = null,
)