package com.progressterra.ipbandroidview.pages.newwithdrawal

import com.progressterra.ipbandroidview.features.bankcard.BankCardEvent
import com.progressterra.ipbandroidview.features.bankcard.UseBankCard
import com.progressterra.ipbandroidview.features.topbar.TopBarEvent
import com.progressterra.ipbandroidview.features.topbar.UseTopBar
import com.progressterra.ipbandroidview.shared.ui.button.ButtonEvent
import com.progressterra.ipbandroidview.shared.ui.button.UseButton
import com.progressterra.ipbandroidview.shared.ui.statecolumn.StateColumnEvent
import com.progressterra.ipbandroidview.shared.ui.statecolumn.UseStateColumn
import com.progressterra.ipbandroidview.shared.ui.textfield.TextFieldEvent
import com.progressterra.ipbandroidview.shared.ui.textfield.UseTextField

interface UseNewWithdrawalScreen : UseTopBar, UseStateColumn, UseButton, UseTextField, UseBankCard {

    fun handle(event: NewWithdrawalScreenEvent)

    class Empty : UseNewWithdrawalScreen {
        override fun handle(event: NewWithdrawalScreenEvent) = Unit
        override fun handle(event: TopBarEvent) = Unit
        override fun handle(event: StateColumnEvent) = Unit
        override fun handle(event: ButtonEvent) = Unit
        override fun handle(event: TextFieldEvent) = Unit
        override fun handleEvent(event: BankCardEvent) = Unit
    }
}