package com.progressterra.ipbandroidview.pages.main

import com.progressterra.ipbandroidview.pages.nav.OnAuth
import com.progressterra.ipbandroidview.pages.nav.OnBankCard
import com.progressterra.ipbandroidview.pages.nav.OnGoodsDetails

interface MainScreenNavigation : OnGoodsDetails, OnBankCard, OnAuth {

    fun onWithdrawal()

    fun onBonuses()
}