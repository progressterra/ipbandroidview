package com.progressterra.ipbandroidview.pages.withdrawal

import com.progressterra.ipbandroidview.shared.mvi.OnBack

interface WithdrawalScreenNavigation : OnBack {

    fun onCreateWithdrawal()
}