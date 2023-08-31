package com.progressterra.ipbandroidview.pages.bonusesdetails

import com.progressterra.ipbandroidview.features.bonuses.BonusesEvent
import com.progressterra.ipbandroidview.features.bonuses.UseBonuses
import com.progressterra.ipbandroidview.features.topbar.TopBarEvent
import com.progressterra.ipbandroidview.features.topbar.UseTopBar
import com.progressterra.ipbandroidview.shared.ui.statecolumn.StateColumnEvent
import com.progressterra.ipbandroidview.shared.ui.statecolumn.UseStateColumn

interface UseBonusesDetailsScreen : UseTopBar,
    UseBonuses, UseStateColumn {

    class Empty : UseBonusesDetailsScreen {
        override fun handle(event: BonusesEvent) = Unit

        override fun handle(event: TopBarEvent) = Unit

        override fun handle(event: StateColumnEvent) = Unit
    }
}