package com.progressterra.ipbandroidview.pages.profile

import com.progressterra.ipbandroidview.pages.nav.OnAuth

interface ProfileScreenNavigation : OnAuth {

    fun onWantThis()

    fun onFavorites()

    fun onLogout()

    fun onOrders()

    fun onSupport()

    fun onProfileDetails()

    fun onBankCards()

    fun onDocuments()
}