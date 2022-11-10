package com.progressterra.ipbandroidview.model

import com.progressterra.ipbandroidview.core.IsEmpty

data class DynamicImage(
    val uri: String = "",
    val height: Int = 0,
    val width: Int = 0
) : IsEmpty {

    override fun isEmpty(): Boolean = uri == "" && height == 0 && width == 0
}