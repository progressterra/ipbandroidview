package com.progressterra.ipbandroidview.widgets.proshkacartsummary

import com.progressterra.ipbandroidview.shared.ui.button.ButtonEvent
import com.progressterra.ipbandroidview.shared.ui.button.UseButton

interface UseProshkaCartSummary : UseButton {


    class Empty : UseProshkaCartSummary {

        override fun handle(id: String, event: ButtonEvent) = Unit
    }
}