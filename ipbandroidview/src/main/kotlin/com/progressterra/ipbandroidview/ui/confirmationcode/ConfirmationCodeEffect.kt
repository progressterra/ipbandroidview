package com.progressterra.ipbandroidview.ui.confirmationcode

import androidx.annotation.StringRes

sealed class ConfirmationCodeEffect {

    object Back : ConfirmationCodeEffect()

    object NeedDetails : ConfirmationCodeEffect()

    object SkipDetails : ConfirmationCodeEffect()

    class Toast(@StringRes val message: Int) : ConfirmationCodeEffect()
}
