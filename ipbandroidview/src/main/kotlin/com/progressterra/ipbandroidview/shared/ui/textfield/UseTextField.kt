package com.progressterra.ipbandroidview.shared.ui.textfield

interface UseTextField {

    fun handle(event: TextFieldEvent)

    class Empty : UseTextField {

        override fun handle(event: TextFieldEvent) = Unit
    }
}