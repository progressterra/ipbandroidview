package com.progressterra.ipbandroidview.features.bonuses

import com.progressterra.ipbandroidview.shared.ui.statecolumn.StateColumnEvent
import com.progressterra.ipbandroidview.shared.ui.statecolumn.UseStateColumn

interface UseBonuses : UseStateColumn {

    fun handle(event: BonusesEvent)

    class Empty : UseBonuses {

        override fun handle(event: StateColumnEvent) = Unit

        override fun handle(event: BonusesEvent) = Unit
    }
}