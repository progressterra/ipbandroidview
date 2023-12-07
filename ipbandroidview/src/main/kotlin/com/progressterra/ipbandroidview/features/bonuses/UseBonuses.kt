package com.progressterra.ipbandroidview.features.bonuses

import com.progressterra.ipbandroidview.shared.ui.button.ButtonEvent
import com.progressterra.ipbandroidview.shared.ui.button.UseButton
import com.progressterra.ipbandroidview.shared.ui.statecolumn.StateColumnEvent
import com.progressterra.ipbandroidview.shared.ui.statecolumn.UseStateColumn

interface UseBonuses : UseStateColumn, UseButton {

    fun handle(event: BonusesEvent)

    class Empty : UseBonuses {

        override fun handle(event: ButtonEvent) = Unit

        override fun handle(event: StateColumnEvent) = Unit

        override fun handle(event: BonusesEvent) = Unit
    }
}