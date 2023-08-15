package com.progressterra.ipbandroidview.pages.main

sealed class MainEvent {

    data object OnBonuses : MainEvent()

    class OnItem(val id: String) : MainEvent()
}
