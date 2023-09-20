package com.progressterra.ipbandroidview.pages.withdrawal

import com.progressterra.ipbandroidview.shared.mvi.OnBack
import com.progressterra.ipbandroidview.shared.mvi.OnBankCard

interface WithdrawalScreenNavigation : OnBack, OnBankCard {

    fun onCreateWithdrawal()
}