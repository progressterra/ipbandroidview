package com.progressterra.ipbandroidview.pages.newwithdrawal

import androidx.annotation.StringRes

sealed class NewWithdrawalScreenEvent {

    data object Back : NewWithdrawalScreenEvent()

    class Toast(@StringRes val message: Int) : NewWithdrawalScreenEvent()

}