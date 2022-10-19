package com.progressterra.ipbandroidview.composable.bottombar

import androidx.annotation.DrawableRes
import androidx.annotation.StringRes

data class BottomMenuItem(
    @StringRes val titleId: Int,
    val count: Int = 0,
    @DrawableRes val iconId: Int,
    val onClick: () -> Unit
)
