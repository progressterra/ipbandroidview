package com.progressterra.ipbandroidview.pages.payment

import com.progressterra.ipbandroidview.features.bonusswitch.UseBonusSwitch
import com.progressterra.ipbandroidview.features.paymentmethod.PaymentMethodEvent
import com.progressterra.ipbandroidview.features.paymentmethod.UsePaymentMethod
import com.progressterra.ipbandroidview.features.topbar.UseTopBar
import com.progressterra.ipbandroidview.features.receipt.UseReceipt
import com.progressterra.ipbandroidview.features.receivereceipt.UseReceiveReceipt
import com.progressterra.ipbandroidview.features.topbar.TopBarEvent
import com.progressterra.ipbandroidview.shared.ui.brushedswitch.BrushedSwitchEvent
import com.progressterra.ipbandroidview.shared.ui.button.ButtonEvent
import com.progressterra.ipbandroidview.shared.ui.button.UseButton
import com.progressterra.ipbandroidview.shared.ui.linktext.LinkTextEvent
import com.progressterra.ipbandroidview.shared.ui.statebox.StateBoxEvent
import com.progressterra.ipbandroidview.shared.ui.statebox.UseStateBox
import com.progressterra.ipbandroidview.shared.ui.textfield.TextFieldEvent

interface UsePayment : UseTopBar, UsePaymentMethod, UseStateBox, UseButton, UseBonusSwitch,
    UseReceiveReceipt, UseReceipt {

        class Empty : UsePayment {
            
            override fun handle(event: PaymentMethodEvent) = Unit

            override fun handle(event: TopBarEvent) = Unit

            override fun handle(event: BrushedSwitchEvent) = Unit

            override fun handle(event: ButtonEvent) = Unit

            override fun handle(event: LinkTextEvent) = Unit

            override fun handle(event: StateBoxEvent) = Unit

            override fun handle(event: TextFieldEvent) = Unit
        }
    }