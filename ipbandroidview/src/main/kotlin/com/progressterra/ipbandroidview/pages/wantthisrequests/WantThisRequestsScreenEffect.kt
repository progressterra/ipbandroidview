package com.progressterra.ipbandroidview.pages.wantthisrequests

import com.progressterra.ipbandroidview.entities.Document

sealed class WantThisRequestsScreenEffect {

    data object Back : WantThisRequestsScreenEffect()

    class RequestDetails(val data: Document) : WantThisRequestsScreenEffect()
}