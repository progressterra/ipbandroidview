package com.progressterra.ipbandroidview.pages.bonusesdetails

import com.progressterra.ipbandroidview.features.bonuses.BonusesEvent
import com.progressterra.ipbandroidview.features.bonuses.UseBonuses
import com.progressterra.ipbandroidview.features.topbar.TopBarEvent
import com.progressterra.ipbandroidview.features.topbar.UseTopBar
import com.progressterra.ipbandroidview.shared.ui.statebox.StateBoxEvent
import com.progressterra.ipbandroidview.shared.ui.statebox.UseStateBox

interface UseBonusesDetails : UseTopBar,
    UseBonuses, UseStateBox {

    class Empty : UseBonusesDetails {
        override fun handle(event: BonusesEvent) = Unit

        override fun handle(event: TopBarEvent) = Unit

        override fun handle(event: StateBoxEvent) = Unit
    }
}