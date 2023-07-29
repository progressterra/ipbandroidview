package com.progressterra.ipbandroidview.pages.wantthisrequests

import com.progressterra.ipbandroidview.features.wantthiscard.WantThisCardState
import com.progressterra.ipbandroidview.shared.PagingUseCase

interface WantThisRequestsUseCase : PagingUseCase<Nothing, WantThisCardState> {

    class Base(
        wantThisRequestsSource: WantThisRequestsSource
    ) : WantThisRequestsUseCase,
        PagingUseCase.Abstract<Nothing, WantThisCardState>(wantThisRequestsSource)
}