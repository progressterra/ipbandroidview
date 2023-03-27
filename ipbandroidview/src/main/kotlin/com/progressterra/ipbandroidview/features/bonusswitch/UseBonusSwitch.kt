package com.progressterra.ipbandroidview.features.bonusswitch

import com.progressterra.ipbandroidview.shared.ui.brushedswitch.UseBrushedSwitch

interface UseBonusSwitch : UseBrushedSwitch {

    fun handle(id: String, event: BonusSwitchEvent)
}