package com.progressterra.ipbandroidview.pages.withdrawal

import com.progressterra.ipbandroidview.pages.nav.OnBack
import com.progressterra.ipbandroidview.pages.nav.OnBankCard

interface WithdrawalScreenNavigation : OnBack, OnBankCard {

    fun onCreateWithdrawal()
}