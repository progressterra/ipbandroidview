package com.progressterra.ipbandroidview.pages.wantthisrequests

import com.progressterra.ipbandroidview.entities.Document
import com.progressterra.ipbandroidview.shared.mvi.OnBack

interface WantThisRequestsScreenNavigation : OnBack {

    fun onRequestDetails(data: Document)
}