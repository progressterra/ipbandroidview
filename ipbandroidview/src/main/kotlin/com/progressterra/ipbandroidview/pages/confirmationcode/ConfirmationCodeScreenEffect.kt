package com.progressterra.ipbandroidview.pages.confirmationcode

import androidx.annotation.StringRes

sealed class ConfirmationCodeScreenEffect {

    data object Skip : ConfirmationCodeScreenEffect()

    data object Back : ConfirmationCodeScreenEffect()

    data object Next : ConfirmationCodeScreenEffect()

    class Toast(@StringRes val data: Int) : ConfirmationCodeScreenEffect()
}
