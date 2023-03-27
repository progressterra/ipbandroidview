package com.progressterra.ipbandroidview.pages.proshkamain

import androidx.compose.runtime.Immutable
import com.progressterra.ipbandroidview.features.proshkabonuses.ProshkaBonusesState
import com.progressterra.ipbandroidview.shared.ui.StateBoxState
import com.progressterra.ipbandroidview.widgets.proshkagalleries.ProshkaGalleriesState
import com.progressterra.ipbandroidview.widgets.proshkaoffers.ProshkaOffersState

@Immutable
data class ProshkaMainState(
    val bonuses: ProshkaBonusesState = ProshkaBonusesState(),
    val offers: ProshkaOffersState = ProshkaOffersState(),
    val stateBoxState: StateBoxState = StateBoxState(),
    val hits: ProshkaGalleriesState = ProshkaGalleriesState(),
    val new: ProshkaGalleriesState = ProshkaGalleriesState()
)
