package com.progressterra.ipbandroidview.ui.confirmationcode

import androidx.annotation.StringRes

sealed class ConfirmationEffect {

    object OnBack : ConfirmationEffect()

    object OnNext : ConfirmationEffect()

    class OnToast(@StringRes val message: Int) : ConfirmationEffect()
}
