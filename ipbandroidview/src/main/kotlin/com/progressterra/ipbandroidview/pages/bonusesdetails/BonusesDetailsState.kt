package com.progressterra.ipbandroidview.pages.bonusesdetails

import androidx.compose.runtime.Immutable
import arrow.optics.optics
import com.progressterra.ipbandroidview.features.bonuses.BonusesState
import com.progressterra.ipbandroidview.shared.ScreenState
import com.progressterra.ipbandroidview.widgets.bonusestransactions.BonusesTransactionsState

@Immutable
@optics data class BonusesDetailsState(
    val bonusesInfo: BonusesState = BonusesState(),
    val transactions: BonusesTransactionsState = BonusesTransactionsState(),
    val screenState: ScreenState = ScreenState.LOADING
) { companion object }
