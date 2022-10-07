package com.progressterra.ipbandroidview.composable.bottomnav

import androidx.annotation.DrawableRes
import androidx.annotation.StringRes

data class BottomMenuItem(
    val id: String,
    @StringRes val titleId: Int,
    val count: Int = 0,
    val active: Boolean,
    @DrawableRes val iconId: Int
)
