package com.progressterra.ipbandroidview.features.code

sealed class CodeEvent {

    class Changed(val code: String) : CodeEvent()
}