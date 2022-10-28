package com.progressterra.ipbandroidview.theme

import com.progressterra.ipbandroidview.dto.DynamicImage

data class Customization(
    val splashImage: DynamicImage = DynamicImage(),
    val signInImage: DynamicImage = DynamicImage(),
    val catalogStyle: CatalogStyle = CatalogStyle()
) {

    data class CatalogStyle(
        val columns: Int = 2
    )
}
