package com.progressterra.ipbandroidview.shared.ui.button

sealed class ButtonEvent(val id: String) {

    class Click(id: String) : ButtonEvent(id)
}