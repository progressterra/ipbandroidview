package com.progressterra.ipbandroidview.ui.main

import android.graphics.Bitmap
import com.progressterra.ipbandroidview.composable.component.BonusesState
import com.progressterra.ipbandroidview.composable.component.StoreCardComponentState
import com.progressterra.ipbandroidview.core.ScreenState
import com.progressterra.ipbandroidview.model.Category

data class MainState(
    val recommended: List<Pair<Category, List<StoreCardComponentState>>> = emptyList(),
    val screenState: ScreenState = ScreenState.LOADING,
    val bonusesState: BonusesState = BonusesState(),
    val userExist: Boolean = false,
    val qr: Bitmap? = null,
)