package com.progressterra.ipbandroidview.ui.main

import android.graphics.Bitmap
import com.progressterra.ipbandroidview.features.proshkabonuses.ProshkaBonusesState
import com.progressterra.ipbandroidview.composable.component.StoreCardComponentState
import com.progressterra.ipbandroidview.core.ScreenState
import com.progressterra.ipbandroidview.model.Category

data class MainState(
    val recommended: List<Pair<Category, List<StoreCardComponentState>>> = emptyList(),
    val screenState: ScreenState = ScreenState.LOADING,
    val extendedBonusesState: ProshkaBonusesState = ProshkaBonusesState(),
    val userExist: Boolean = false,
    val qr: Bitmap? = null,
)