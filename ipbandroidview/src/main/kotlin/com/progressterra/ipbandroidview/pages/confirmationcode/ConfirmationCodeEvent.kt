package com.progressterra.ipbandroidview.pages.confirmationcode

import androidx.annotation.StringRes

sealed class ConfirmationCodeEvent {

    data object Back : ConfirmationCodeEvent()

    data object Next : ConfirmationCodeEvent()

    class Toast(@StringRes val message: Int) : ConfirmationCodeEvent()
}
