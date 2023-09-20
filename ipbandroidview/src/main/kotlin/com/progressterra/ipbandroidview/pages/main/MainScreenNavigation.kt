package com.progressterra.ipbandroidview.pages.main

import com.progressterra.ipbandroidview.shared.mvi.OnBankCard
import com.progressterra.ipbandroidview.shared.mvi.OpenGoodsDetails

interface MainScreenNavigation : OpenGoodsDetails, OnBankCard {

    fun onWithdrawal()

    fun onBonuses()
}