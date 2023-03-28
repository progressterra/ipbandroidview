package com.progressterra.ipbandroidview.features.confirmationcode

import com.progressterra.ipbandroidview.composable.component.UseTextButton
import com.progressterra.ipbandroidview.shared.ui.button.UseButton

interface UseConfirmationCode {

    fun handle(event: ConfirmationCodeEvent)
}