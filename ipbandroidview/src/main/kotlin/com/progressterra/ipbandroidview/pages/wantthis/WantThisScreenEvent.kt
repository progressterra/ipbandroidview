package com.progressterra.ipbandroidview.pages.wantthis

import androidx.annotation.StringRes

sealed class WantThisScreenEvent {

    data object Requests : WantThisScreenEvent()

    data object Back : WantThisScreenEvent()

    class OpenPhoto(val url: String) : WantThisScreenEvent()

    class Toast(@StringRes val message: Int) : WantThisScreenEvent()
}