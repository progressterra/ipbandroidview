package com.progressterra.ipbandroidview.ui.confirmationcode

import androidx.annotation.StringRes

sealed class ConfirmationCodeEffect {

    object Back : ConfirmationCodeEffect()

    object OpenNext : ConfirmationCodeEffect()

    @Suppress("unused")
    class ShowToast(@StringRes val message: Int) : ConfirmationCodeEffect()
}
