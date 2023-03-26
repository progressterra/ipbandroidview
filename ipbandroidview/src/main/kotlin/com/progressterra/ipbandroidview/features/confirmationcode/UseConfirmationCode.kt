package com.progressterra.ipbandroidview.features.confirmationcode

import com.progressterra.ipbandroidview.composable.component.UseTextButton
import com.progressterra.ipbandroidview.shared.ui.button.UseButton

interface UseConfirmationCode : UseButton, UseTextButton {

    fun handleEvent(id: String, event: ConfirmationCodeEvent)
}