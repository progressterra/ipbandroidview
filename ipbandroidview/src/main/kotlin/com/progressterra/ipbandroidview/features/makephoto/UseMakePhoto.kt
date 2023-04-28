package com.progressterra.ipbandroidview.features.makephoto

import com.progressterra.ipbandroidview.shared.ui.button.ButtonEvent
import com.progressterra.ipbandroidview.shared.ui.button.UseButton

interface UseMakePhoto : UseButton {

    fun handle(event: MakePhotoEvent)

    class Empty : UseMakePhoto {

        override fun handle(event: MakePhotoEvent) = Unit

        override fun handle(event: ButtonEvent) = Unit
    }
}