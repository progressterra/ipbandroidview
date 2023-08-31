package com.progressterra.ipbandroidview.pages.newwithdrawal

import androidx.annotation.StringRes

sealed class NewWithdrawalScreenEffect {

    data object Back : NewWithdrawalScreenEffect()

    class Toast(@StringRes val data: Int) : NewWithdrawalScreenEffect()

}