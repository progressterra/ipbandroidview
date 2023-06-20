package com.progressterra.ipbandroidview.features.bottombar

sealed class RediBottomBarEvent {

    class Activate(val index: Int): RediBottomBarEvent()
}