package com.progressterra.ipbandroidview.ui.checklist

import com.progressterra.ipbandroidview.shared.ui.ButtonEvent
import com.progressterra.ipbandroidview.shared.ui.TextFieldEvent
import com.progressterra.ipbandroidview.model.Check

interface ChecklistInteractor : UseCurrentCheck {

    fun onBack()

    fun refreshChecklist()

    fun openCheck(check: Check)

    class Empty : ChecklistInteractor {

        override fun handleEvent(id: String, event: ButtonEvent) = Unit

        override fun handleEvent(id: String, event: TextFieldEvent) = Unit

        override fun handleEvent(id: String, event: CurrentCheckEvent) = Unit

        override fun onBack() = Unit

        override fun refreshChecklist() = Unit

        override fun openCheck(check: Check) = Unit
    }
}