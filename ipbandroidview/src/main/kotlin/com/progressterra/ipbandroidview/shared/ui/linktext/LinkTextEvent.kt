package com.progressterra.ipbandroidview.shared.ui.linktext

sealed class LinkTextEvent {

    class Click(val url: String) : LinkTextEvent()
}