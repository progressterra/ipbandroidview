package com.progressterra.ipbandroidview.features.bonusswitch

import com.progressterra.ipbandroidview.shared.ui.brushedswitch.BrushedSwitchEvent
import com.progressterra.ipbandroidview.shared.ui.brushedswitch.UseBrushedSwitch

interface UseBonusSwitch : UseBrushedSwitch {

    fun handle(event: BonusSwitchEvent)

    class Empty : UseBonusSwitch {

        override fun handle(event: BonusSwitchEvent) = Unit

        override fun handle(event: BrushedSwitchEvent) = Unit
    }
}