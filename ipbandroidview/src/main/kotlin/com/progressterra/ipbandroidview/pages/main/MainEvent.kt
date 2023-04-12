package com.progressterra.ipbandroidview.pages.main

sealed class MainEvent {

    object OnBonuses : MainEvent()

    class OnOffer(val id: String) : MainEvent()

    class OnItem(val id: String) : MainEvent()
}
