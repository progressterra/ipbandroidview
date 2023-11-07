package com.progressterra.ipbandroidview.widgets.edituser

import com.progressterra.ipbandroidview.shared.ui.textfield.UseTextField

interface UseEditUser : UseTextField {

    fun handle(event: EditUserEvent)
}
