package com.progressterra.ipbandroidview.city

interface CityInteractor {

    fun onBack()

    fun onSkip()

    fun onNext()

    fun onAddress(address: String)

    class Empty : CityInteractor {

        override fun onBack() = Unit

        override fun onSkip() = Unit

        override fun onNext() = Unit

        override fun onAddress(address: String) = Unit
    }
}