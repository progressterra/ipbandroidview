package com.progressterra.ipbandroidview.theme

import androidx.compose.runtime.Immutable
import com.progressterra.ipbandroidview.dto.DynamicImage

@Immutable
data class Customization(
    val splashImage: DynamicImage = DynamicImage(),
    val signInImage: DynamicImage = DynamicImage(),
    val catalogStyle: CatalogStyle = CatalogStyle()
) {

    data class CatalogStyle(
        val columns: Int = 2
    )
}
