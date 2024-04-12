package com.progressterra.ipbandroidview.features.bonusswitch

import com.progressterra.ipbandroidview.shared.ui.brushedswitch.SwitchEvent
import com.progressterra.ipbandroidview.shared.ui.brushedswitch.UseSwitch

interface UseBonusSwitch : UseSwitch {

    class Empty : UseBonusSwitch {

        override fun handle(event: SwitchEvent) = Unit
    }
}