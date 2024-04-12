package com.progressterra.ipbandroidview.features.bonusswitch

import com.progressterra.ipbandroidview.shared.ui.switch.SwitchEvent
import com.progressterra.ipbandroidview.shared.ui.switch.UseSwitch

interface UseBonusSwitch : UseSwitch {

    class Empty : UseBonusSwitch {

        override fun handle(event: SwitchEvent) = Unit
    }
}