package com.progressterra.ipbandroidview.pages.wantthisrequests

sealed class WantThisRequestsEvent {

    object Back : WantThisRequestsEvent()

    class GoodsDetails(val id: String) : WantThisRequestsEvent()
}