package com.progressterra.ipbandroidview.ui.city

sealed class CityEffect {

    object Skip : CityEffect()

    object OpenNext : CityEffect()

    object Back : CityEffect()
}
