package com.progressterra.ipbandroidview.pages.checklist

import com.progressterra.ipbandroidview.features.topbar.TopBarEvent
import com.progressterra.ipbandroidview.features.topbar.UseTopBar
import com.progressterra.ipbandroidview.shared.ui.button.ButtonEvent
import com.progressterra.ipbandroidview.shared.ui.button.UseButton
import com.progressterra.ipbandroidview.shared.ui.statecolumn.StateColumnEvent
import com.progressterra.ipbandroidview.shared.ui.statecolumn.UseStateColumn
import com.progressterra.ipbandroidview.shared.ui.textfield.TextFieldEvent
import com.progressterra.ipbandroidview.shared.ui.textfield.UseTextField

interface UseChecklistScreen : UseTextField, UseButton, UseStateColumn, UseTopBar {

    fun handle(event: ChecklistEvent)

    class Empty : UseChecklistScreen {

        override fun handle(event: TopBarEvent) = Unit

        override fun handle(event: ChecklistEvent) = Unit

        override fun handle(event: ButtonEvent) = Unit

        override fun handle(event: StateColumnEvent) = Unit

        override fun handle(event: TextFieldEvent) = Unit
    }
}