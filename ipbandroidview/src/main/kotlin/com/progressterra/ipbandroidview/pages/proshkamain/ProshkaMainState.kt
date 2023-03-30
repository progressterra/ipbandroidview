package com.progressterra.ipbandroidview.pages.proshkamain

import androidx.compose.runtime.Immutable
import androidx.paging.PagingData
import com.progressterra.ipbandroidview.core.ScreenState
import com.progressterra.ipbandroidview.features.proshkabonuses.ProshkaBonusesState
import com.progressterra.ipbandroidview.features.proshkastorecard.ProshkaStoreCardState
import com.progressterra.ipbandroidview.shared.ui.statebox.StateBoxState
import com.progressterra.ipbandroidview.widgets.proshkagalleries.ProshkaGalleriesState
import com.progressterra.ipbandroidview.widgets.proshkaoffers.ProshkaOffersState
import kotlinx.coroutines.flow.Flow

@Immutable
data class ProshkaMainState(
    val bonuses: ProshkaBonusesState = ProshkaBonusesState(),
    val offers: ProshkaOffersState = ProshkaOffersState(),
    val stateBoxState: StateBoxState = StateBoxState(),
    val hits: ProshkaGalleriesState = ProshkaGalleriesState(),
    val new: ProshkaGalleriesState = ProshkaGalleriesState()
) {

    fun updateStateBox(screenState: ScreenState) =
        copy(stateBoxState = stateBoxState.updateState(screenState))

    fun updateHits(newHits: Flow<PagingData<ProshkaStoreCardState>>) =
        copy(hits = hits.updateItems(newHits))

    fun updateNew(newNew: Flow<PagingData<ProshkaStoreCardState>>) =
        copy(new = new.updateItems(newNew))

    fun updateBonuses(bonuses: ProshkaBonusesState) = copy(bonuses = bonuses)
    fun reverseBonuses() = copy(bonuses = bonuses.reverse())

    fun updateOffers(offers: ProshkaOffersState) = copy(offers = offers)
}
