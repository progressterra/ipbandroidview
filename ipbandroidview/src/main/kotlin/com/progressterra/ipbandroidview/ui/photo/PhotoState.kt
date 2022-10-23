package com.progressterra.ipbandroidview.ui.photo

import android.graphics.Bitmap
import com.progressterra.ipbandroidview.core.Picture

data class PhotoState(
    val picture: Picture? = null,
    val bitmap: Bitmap? = null
)
