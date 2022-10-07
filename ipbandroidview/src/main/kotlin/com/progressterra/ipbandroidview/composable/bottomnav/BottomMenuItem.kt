package com.progressterra.ipbandroidview.composable.bottomnav

import androidx.annotation.DrawableRes

data class BottomMenuItem(
    val title: String = "",
    val count: Int = 0,
    val active: Boolean,
    @DrawableRes val iconId: Int
)
