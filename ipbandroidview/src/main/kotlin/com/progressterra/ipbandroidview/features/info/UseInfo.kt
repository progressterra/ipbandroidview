package com.progressterra.ipbandroidview.features.info

import com.progressterra.ipbandroidview.shared.ui.textfield.TextFieldEvent
import com.progressterra.ipbandroidview.shared.ui.textfield.UseTextField

interface UseInfo : UseTextField {

    class Empty : UseInfo {

        override fun handle(event: TextFieldEvent) = Unit
    }
}