package com.progressterra.ipbandroidview.widgets.proshkacartsummary

import com.progressterra.ipbandroidview.shared.ui.button.ButtonEvent
import com.progressterra.ipbandroidview.shared.ui.button.UseButton

interface UseCartSummary : UseButton {


    class Empty : UseCartSummary {

        override fun handle(event: ButtonEvent) = Unit
    }
}