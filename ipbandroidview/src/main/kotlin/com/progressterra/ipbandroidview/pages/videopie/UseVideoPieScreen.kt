package com.progressterra.ipbandroidview.pages.videopie

import com.progressterra.ipbandroidview.shared.ui.statecolumn.StateColumnEvent
import com.progressterra.ipbandroidview.shared.ui.statecolumn.UseStateColumn

interface UseVideoPieScreen : UseStateColumn {

    fun handle(event: VideoPieScreenEvent)

    class Empty : UseVideoPieScreen {

        override fun handle(event: StateColumnEvent) = Unit

        override fun handle(event: VideoPieScreenEvent) = Unit
    }
}