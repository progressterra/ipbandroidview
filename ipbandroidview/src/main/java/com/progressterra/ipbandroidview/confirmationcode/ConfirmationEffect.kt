package com.progressterra.ipbandroidview.confirmationcode

import androidx.annotation.StringRes

sealed class ConfirmationEffect {

    object Back : ConfirmationEffect()

    object Next : ConfirmationEffect()

    class Toast(@StringRes val message: Int) : ConfirmationEffect()
}
