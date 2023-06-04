package com.progressterra.ipbandroidview.shared.ui.button

import com.progressterra.ipbandroidview.entities.Id

sealed class ButtonEvent(override val id: String) : Id {

    class Click(id: String) : ButtonEvent(id)
}