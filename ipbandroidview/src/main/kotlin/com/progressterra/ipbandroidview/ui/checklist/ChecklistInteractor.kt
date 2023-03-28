package com.progressterra.ipbandroidview.ui.checklist

import com.progressterra.ipbandroidview.shared.ui.button.ButtonEvent
import com.progressterra.ipbandroidview.shared.ui.textfield.TextFieldEvent
import com.progressterra.ipbandroidview.entities.Check

interface ChecklistInteractor : UseCurrentCheck {

    fun onBack()

    fun refreshChecklist()

    fun openCheck(check: Check)

    class Empty : ChecklistInteractor {

        override fun handle(event: ButtonEvent) = Unit

        override fun handle(event: TextFieldEvent) = Unit

        override fun handle(event: CurrentCheckEvent) = Unit

        override fun onBack() = Unit

        override fun refreshChecklist() = Unit

        override fun openCheck(check: Check) = Unit
    }
}