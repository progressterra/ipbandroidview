package com.progressterra.ipbandroidview.pages.profile

import com.progressterra.ipbandroidview.shared.mvi.OnAuth

interface ProfileScreenNavigation : OnAuth {

    fun onFavorites()

    fun onLogout()

    fun onOrders()

    fun onSupport()

    fun onProfileDetails()

    fun onBankCards()

    fun onDocuments()
}