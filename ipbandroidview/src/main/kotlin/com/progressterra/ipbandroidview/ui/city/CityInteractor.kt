package com.progressterra.ipbandroidview.ui.city

import com.progressterra.ipbandroidview.composable.component.MapEvent

interface CityInteractor : MapComponentEventHandler {

    fun onBack()

    fun onSkip()

    fun onNext()

    class Empty : CityInteractor {

        override fun onBack() = Unit

        override fun onSkip() = Unit

        override fun onNext() = Unit

        override fun handle(event: MapEvent) = Unit
    }
}