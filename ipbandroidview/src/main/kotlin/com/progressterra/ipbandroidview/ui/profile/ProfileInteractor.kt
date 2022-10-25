package com.progressterra.ipbandroidview.ui.profile

interface ProfileInteractor {

    fun openDetails()

    class Empty : ProfileInteractor {

        override fun openDetails() = Unit
    }
}