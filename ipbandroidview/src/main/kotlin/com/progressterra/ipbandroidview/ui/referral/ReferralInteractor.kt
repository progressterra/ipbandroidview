package com.progressterra.ipbandroidview.ui.referral

interface ReferralInteractor {

    fun onBack()

    fun copy()

    fun share()

    fun refresh()

    class Empty : ReferralInteractor {

        override fun onBack() = Unit

        override fun copy() = Unit

        override fun share() = Unit

        override fun refresh() = Unit
    }
}