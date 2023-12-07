package com.progressterra.ipbandroidview.pages.main

sealed class MainScreenEffect {

    data object OnAuth : MainScreenEffect()

    data object OnAddCard : MainScreenEffect()

    data object OnBonuses : MainScreenEffect()

    data object OnWithdrawal : MainScreenEffect()

    class OnItem(val data: String) : MainScreenEffect()
}
