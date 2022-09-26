package com.progressterra.ipbandroidview.city

sealed class CityEffect {

    object Back : CityEffect()

    object Skip : CityEffect()

    object Next : CityEffect()
}
