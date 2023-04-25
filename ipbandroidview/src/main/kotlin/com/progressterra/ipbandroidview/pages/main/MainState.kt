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

    fun uStateBox(screenState: ScreenState) = copy(stateBox = screenState)

    fun uHits(newHits: Flow<PagingData<StoreCardState>>) =
        copy(hits = hits.uItems(newHits))

    fun uNew(newNew: Flow<PagingData<StoreCardState>>) =
        copy(new = new.uItems(newNew))

    fun uBonuses(bonuses: BonusesState) = copy(bonuses = bonuses)

    fun uOffers(offers: OffersState) = copy(offers = offers)
}
