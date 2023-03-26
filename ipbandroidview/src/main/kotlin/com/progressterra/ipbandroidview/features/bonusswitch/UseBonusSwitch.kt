package com.progressterra.ipbandroidview.features.bonusswitch

import com.progressterra.ipbandroidview.shared.ui.brushedswitch.UseBrushedSwitch

interface UseBonusSwitch : UseBrushedSwitch {

    fun handleEvent(id: String, event: BonusSwitchEvent)
}