package com.progressterra.ipbandroidview.pages.proshkamain

import androidx.compose.runtime.Immutable
import com.progressterra.ipbandroidview.core.ScreenState
import com.progressterra.ipbandroidview.features.ProshkaBonusesState
import com.progressterra.ipbandroidview.widgets.ProshkaGalleriesState
import com.progressterra.ipbandroidview.widgets.ProshkaOffersState

@Immutable
data class ProshkaMainState(
    val bonuses: ProshkaBonusesState = ProshkaBonusesState(),
    val offers: ProshkaOffersState = ProshkaOffersState(),
    val screenState: ScreenState = ScreenState.LOADING,
    val hits: ProshkaGalleriesState = ProshkaGalleriesState(),
    val new: ProshkaGalleriesState = ProshkaGalleriesState()
)
