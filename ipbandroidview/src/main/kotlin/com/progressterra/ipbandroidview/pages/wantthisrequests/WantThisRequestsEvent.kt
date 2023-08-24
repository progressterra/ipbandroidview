package com.progressterra.ipbandroidview.pages.wantthisrequests

import com.progressterra.ipbandroidview.entities.Document

sealed class WantThisRequestsEvent {

    data object Back : WantThisRequestsEvent()

    class RequestDetails(val document: Document) : WantThisRequestsEvent()
}