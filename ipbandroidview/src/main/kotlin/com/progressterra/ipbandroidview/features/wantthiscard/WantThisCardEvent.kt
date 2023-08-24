package com.progressterra.ipbandroidview.features.wantthiscard

import com.progressterra.ipbandroidview.entities.Document

sealed class WantThisCardEvent {

    data class Buy(val id: String) : WantThisCardEvent()

    data class Open(val document: Document) : WantThisCardEvent()
}