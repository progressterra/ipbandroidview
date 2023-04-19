package com.progressterra.ipbandroidview.pages.main

import androidx.paging.PagingData
import com.progressterra.ipbandroidview.features.bonuses.BonusesState
import com.progressterra.ipbandroidview.features.storecard.StoreCardState
import com.progressterra.ipbandroidview.shared.ScreenState
import com.progressterra.ipbandroidview.widgets.galleries.GalleriesState
import com.progressterra.ipbandroidview.widgets.offers.OffersState
import kotlinx.coroutines.flow.Flow

data class MainState(
    val bonuses: BonusesState = BonusesState(),
    val offers: OffersState = OffersState(),
    val stateBox: ScreenState = ScreenState.LOADING,
    val hits: GalleriesState = GalleriesState(),
    val new: GalleriesState = GalleriesState()
) {

    fun updateStateBox(screenState: ScreenState) = copy(stateBox = screenState)

    fun updateHits(newHits: Flow<PagingData<StoreCardState>>) =
        copy(hits = hits.updateItems(newHits))

    fun updateNew(newNew: Flow<PagingData<StoreCardState>>) =
        copy(new = new.updateItems(newNew))

    fun updateBonuses(bonuses: BonusesState) = copy(bonuses = bonuses)

    fun updateOffers(offers: OffersState) = copy(offers = offers)
}
