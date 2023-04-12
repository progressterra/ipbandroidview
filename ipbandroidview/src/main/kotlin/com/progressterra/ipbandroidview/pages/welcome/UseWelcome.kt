package com.progressterra.ipbandroidview.pages.welcome

import com.progressterra.ipbandroidview.features.authorskip.UseAuthOrSkip
import com.progressterra.ipbandroidview.shared.ui.button.ButtonEvent

interface UseWelcome : UseAuthOrSkip {

    class Empty : UseWelcome {

        override fun handle(event: ButtonEvent) = Unit
    }
}