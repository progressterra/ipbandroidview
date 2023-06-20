package com.progressterra.ipbandroidview.pages.wantthis

sealed class WantThisScreenEvent {

    object Requests : WantThisScreenEvent()

    object Back : WantThisScreenEvent()
}