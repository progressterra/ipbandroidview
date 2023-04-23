package com.progressterra.ipbandroidview.pages.main

sealed class MainEvent {

    object OnBonuses : MainEvent()

    class OnItem(val id: String) : MainEvent()
}
