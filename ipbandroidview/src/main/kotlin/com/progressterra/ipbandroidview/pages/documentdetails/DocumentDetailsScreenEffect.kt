package com.progressterra.ipbandroidview.pages.documentdetails

sealed class DocumentDetailsScreenEffect {

    data object Back : DocumentDetailsScreenEffect()

    class OpenPhoto(val data: String) : DocumentDetailsScreenEffect()
}