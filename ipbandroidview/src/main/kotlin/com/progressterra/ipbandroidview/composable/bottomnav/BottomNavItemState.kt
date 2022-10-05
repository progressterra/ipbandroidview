package com.progressterra.ipbandroidview.composable.bottomnav

import androidx.annotation.DrawableRes

data class BottomNavItemState(
    val title: String = "",
    val count: Int = 0,
    val active: Boolean,
    @DrawableRes val iconId: Int
)
