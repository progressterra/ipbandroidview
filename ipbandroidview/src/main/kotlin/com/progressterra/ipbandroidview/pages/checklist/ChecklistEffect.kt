package com.progressterra.ipbandroidview.pages.checklist

import com.progressterra.ipbandroidview.entities.MultisizedImage

sealed class ChecklistEffect {

    data object OnBack : ChecklistEffect()

    class OnImage(val picture: MultisizedImage, val enabled: Boolean) : ChecklistEffect()
}