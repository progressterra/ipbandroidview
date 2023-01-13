package com.progressterra.ipbandroidview.ui.profile

interface ProfileInteractor {

    fun openDetails()

    fun onOrders()

    fun onFavorites()

    fun onSupport()

    fun onReferral()

    class Empty : ProfileInteractor {

        override fun openDetails() = Unit

        override fun onOrders() = Unit

        override fun onFavorites() = Unit

        override fun onSupport() = Unit

        override fun onReferral() = Unit
    }
}