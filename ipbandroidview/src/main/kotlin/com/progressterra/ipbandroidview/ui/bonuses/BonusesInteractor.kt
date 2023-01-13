package com.progressterra.ipbandroidview.ui.bonuses

interface BonusesInteractor {

    fun onClarification()

    fun onBack()

    fun refresh()

    class Empty : BonusesInteractor {

        override fun onClarification() = Unit

        override fun onBack() = Unit

        override fun refresh() = Unit
    }
}