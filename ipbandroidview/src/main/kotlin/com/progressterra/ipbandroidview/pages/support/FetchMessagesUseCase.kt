package com.progressterra.ipbandroidview.pages.support

import com.progressterra.ipbandroidview.entities.Message
import com.progressterra.ipbandroidview.shared.PagingUseCase

interface FetchMessagesUseCase : PagingUseCase<String, Message> {


}