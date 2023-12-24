package com.progressterra.ipbandroidview.pages.checklist

import com.progressterra.ipbandroidview.entities.MultisizedImage

sealed class ChecklistScreenEffect {

    data object OnBack : ChecklistScreenEffect()

    class OnImage(val picture: MultisizedImage) : ChecklistScreenEffect()
}