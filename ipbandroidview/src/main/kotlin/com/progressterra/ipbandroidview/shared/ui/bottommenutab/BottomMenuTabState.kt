package com.progressterra.ipbandroidview.shared.ui.bottommenutab

import androidx.annotation.DrawableRes
import androidx.annotation.StringRes
import androidx.compose.runtime.Immutable

@Immutable
data class BottomMenuTabState(
    val id: Int,
    val count: Int = 0,
    @StringRes val titleId: Int,
    @DrawableRes val iconId: Int
)