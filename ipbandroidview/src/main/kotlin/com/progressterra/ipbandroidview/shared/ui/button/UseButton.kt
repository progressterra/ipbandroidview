package com.progressterra.ipbandroidview.shared.ui.button

interface UseButton {

    fun handle(event: ButtonEvent)

    class Empty : UseButton {

        override fun handle(event: ButtonEvent) = Unit
    }
}