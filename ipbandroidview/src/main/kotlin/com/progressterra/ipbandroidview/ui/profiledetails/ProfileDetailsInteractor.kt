package com.progressterra.ipbandroidview.ui.profiledetails

import com.progressterra.ipbandroidview.composable.component.ButtonEvent
import com.progressterra.ipbandroidview.composable.component.TextFieldEvent
import com.progressterra.ipbandroidview.composable.component.UseButton
import com.progressterra.ipbandroidview.composable.component.UseTextField

interface ProfileDetailsInteractor : UseTextField, UseButton {

    fun onBack()

    fun mailToInfo(address: String)

    class Empty : ProfileDetailsInteractor {

        override fun mailToInfo(address: String) = Unit

        override fun handleEvent(id: String, event: ButtonEvent) = Unit

        override fun handleEvent(id: String, event: TextFieldEvent) = Unit

        override fun onBack() = Unit
    }
}