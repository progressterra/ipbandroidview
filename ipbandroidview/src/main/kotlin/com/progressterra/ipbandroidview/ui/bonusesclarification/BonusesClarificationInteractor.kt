package com.progressterra.ipbandroidview.ui.bonusesclarification

interface BonusesClarificationInteractor {

    fun onBack()

    fun expandHowToSpend()

    fun expandRatio()

    fun expandHowToObtain()

    class Empty : BonusesClarificationInteractor {

        override fun onBack() = Unit

        override fun expandHowToSpend() = Unit

        override fun expandRatio() = Unit

        override fun expandHowToObtain() = Unit
    }
}