package com.progressterra.ipbandroidview.pages.confirmationcode

import androidx.annotation.StringRes

sealed class ConfirmationCodeEvent {

    object Back : ConfirmationCodeEvent()

    object Next : ConfirmationCodeEvent()

    class Toast(@StringRes val message: Int) : ConfirmationCodeEvent()
}
