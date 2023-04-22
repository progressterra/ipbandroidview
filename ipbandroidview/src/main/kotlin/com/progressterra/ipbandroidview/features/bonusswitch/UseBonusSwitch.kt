package com.progressterra.ipbandroidview.features.bonusswitch

import com.progressterra.ipbandroidview.shared.ui.brushedswitch.BrushedSwitchEvent
import com.progressterra.ipbandroidview.shared.ui.brushedswitch.UseBrushedSwitch

interface UseBonusSwitch : UseBrushedSwitch {

    class Empty : UseBonusSwitch {

        override fun handle(event: BrushedSwitchEvent) = Unit
    }
}