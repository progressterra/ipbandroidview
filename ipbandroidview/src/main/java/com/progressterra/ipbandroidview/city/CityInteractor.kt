package com.progressterra.ipbandroidview.city

interface CityInteractor {

    fun onBack()

    fun onSkip()

    fun onNext()

    fun onAddress(address: String)

    fun onAddressFocusChanged(isFocused: Boolean)

    class Empty : CityInteractor {

        override fun onBack() = Unit

        override fun onSkip() = Unit

        override fun onNext() = Unit

        override fun onAddress(address: String) = Unit

        override fun onAddressFocusChanged(isFocused: Boolean) = Unit
    }
}