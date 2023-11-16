package com.progressterra.ipbandroidview.pages.main

import com.progressterra.ipbandroidview.pages.nav.OnBankCard
import com.progressterra.ipbandroidview.pages.nav.OnGoodsDetails

interface MainScreenNavigation : OnGoodsDetails, OnBankCard {

    fun onWithdrawal()

    fun onBonuses()
}