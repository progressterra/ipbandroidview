package com.progressterra.ipbandroidview.theme

import androidx.compose.runtime.Immutable

@Immutable
data class Customization(
    val splashImage: String = "",
    val catalogStyle: CatalogStyle = CatalogStyle()
) {

    data class CatalogStyle(
        val columns: Int = 2
    )
}
