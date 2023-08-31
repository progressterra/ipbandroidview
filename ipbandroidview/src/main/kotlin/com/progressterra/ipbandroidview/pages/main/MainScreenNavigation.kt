package com.progressterra.ipbandroidview.pages.main

import com.progressterra.ipbandroidview.shared.mvi.OnBack
import com.progressterra.ipbandroidview.shared.mvi.OpenGoodsDetails

interface MainScreenNavigation : OpenGoodsDetails {

    fun onWithdrawal()

    fun onBonuses()
}