package com.progressterra.ipbandroidview.pages.videopie

import com.progressterra.ipbandroidview.shared.ui.button.ButtonEvent
import com.progressterra.ipbandroidview.shared.ui.button.UseButton
import com.progressterra.ipbandroidview.shared.ui.statecolumn.StateColumnEvent
import com.progressterra.ipbandroidview.shared.ui.statecolumn.UseStateColumn

interface UseVideoPieScreen : UseStateColumn, UseButton {

    fun handle(event: VideoPieScreenEvent)

    class Empty : UseVideoPieScreen {

        override fun handle(event: ButtonEvent) = Unit

        override fun handle(event: StateColumnEvent) = Unit

        override fun handle(event: VideoPieScreenEvent) = Unit
    }
}