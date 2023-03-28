package com.progressterra.ipbandroidview.shared.ui.brushedswitch

sealed class BrushedSwitchEvent(val id: String) {

    class Click(id: String) : BrushedSwitchEvent(id)
}