package com.progressterra.ipbandroidview.ui.city

sealed class CityEffect {

    object Back : CityEffect()

    object Skip : CityEffect()

    object Next : CityEffect()
}
