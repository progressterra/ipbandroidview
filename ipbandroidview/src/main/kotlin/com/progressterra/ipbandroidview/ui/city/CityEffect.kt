package com.progressterra.ipbandroidview.ui.city

sealed class CityEffect {

    object Skip : CityEffect()

    object Next : CityEffect()

    object Back : CityEffect()
}
