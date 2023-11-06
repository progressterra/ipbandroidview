package com.progressterra.ipbandroidview.pages.newwithdrawal

import com.progressterra.ipbandroidview.features.bankcard.UseBankCard
import com.progressterra.ipbandroidview.features.topbar.UseTopBar
import com.progressterra.ipbandroidview.shared.ui.button.UseButton
import com.progressterra.ipbandroidview.shared.ui.statecolumn.UseStateColumn
import com.progressterra.ipbandroidview.shared.ui.textfield.UseTextField

interface UseNewWithdrawalScreen : UseTopBar, UseStateColumn, UseButton, UseTextField, UseBankCard {

    fun handle(event: NewWithdrawalScreenEvent)
}