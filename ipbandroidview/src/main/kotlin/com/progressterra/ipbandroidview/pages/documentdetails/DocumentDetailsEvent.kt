package com.progressterra.ipbandroidview.pages.documentdetails

sealed class DocumentDetailsEvent {

    object Back : DocumentDetailsEvent()

    class OpenPhoto(val image: String) : DocumentDetailsEvent()
}