package com.progressterra.ipbandroidview.pages.payment

import com.progressterra.ipbandroidview.features.bonusswitch.UseBonusSwitch
import com.progressterra.ipbandroidview.features.paymentmethod.UsePaymentMethod
import com.progressterra.ipbandroidview.features.proshkatopbar.UseProshkaTopBar
import com.progressterra.ipbandroidview.features.receipt.UseReceipt
import com.progressterra.ipbandroidview.features.receivereceipt.UseReceiveReceipt
import com.progressterra.ipbandroidview.shared.ui.button.UseButton
import com.progressterra.ipbandroidview.shared.ui.statebox.UseStateBox

interface UsePayment : UseProshkaTopBar, UsePaymentMethod, UseStateBox, UseButton, UseBonusSwitch,
    UseReceiveReceipt, UseReceipt