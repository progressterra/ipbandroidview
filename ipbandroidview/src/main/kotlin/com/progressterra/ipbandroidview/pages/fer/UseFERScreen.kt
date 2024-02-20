package com.progressterra.ipbandroidview.pages.fer

import com.progressterra.ipbandroidview.shared.ui.button.UseButton
import com.progressterra.ipbandroidview.shared.ui.textfield.UseTextField

interface UseFERScreen : UseButton, UseTextField {

    fun handle(event: FEREvent)
}