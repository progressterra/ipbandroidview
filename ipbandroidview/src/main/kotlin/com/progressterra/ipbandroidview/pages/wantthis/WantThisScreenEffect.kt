package com.progressterra.ipbandroidview.pages.wantthis

import androidx.annotation.StringRes

sealed class WantThisScreenEffect {

    data object Requests : WantThisScreenEffect()

    class OpenPhoto(val data: String) : WantThisScreenEffect()

    class Toast(@StringRes val data: Int) : WantThisScreenEffect()
}