package com.progressterra.ipbandroidview.ui.confirmationcode

import androidx.annotation.StringRes

sealed class ConfirmationCodeEffect {

    object Back : ConfirmationCodeEffect()

    object Next : ConfirmationCodeEffect()

    @Suppress("unused")
    class Toast(@StringRes val message: Int) : ConfirmationCodeEffect()
}
