package com.progressterra.ipbandroidview.composable.linktext

import androidx.compose.ui.text.AnnotatedString

data class LinkTextData(
    val text: String,
    val tag: String? = null,
    val annotation: String? = null,
    val onClick: ((str: AnnotatedString.Range<String>) -> Unit)? = null,
)