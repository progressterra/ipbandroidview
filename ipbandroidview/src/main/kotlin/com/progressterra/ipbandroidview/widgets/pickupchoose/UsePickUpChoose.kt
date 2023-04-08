package com.progressterra.ipbandroidview.widgets.pickupchoose

import com.progressterra.ipbandroidview.shared.ui.textfield.UseTextField

interface UsePickUpChoose : UseTextField {

    fun handle(event: PickUpChooseEvent)
}