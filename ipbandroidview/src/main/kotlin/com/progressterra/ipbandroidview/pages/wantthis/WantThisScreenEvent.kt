package com.progressterra.ipbandroidview.pages.wantthis

import androidx.annotation.StringRes

sealed class WantThisScreenEvent {

    object Requests : WantThisScreenEvent()

    object Back : WantThisScreenEvent()

    class OpenPhoto(val url: String) : WantThisScreenEvent()

    class Toast(@StringRes val message: Int) : WantThisScreenEvent()
}