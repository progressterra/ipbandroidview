package com.progressterra.ipbandroidview.pages.fer

import com.progressterra.ipbshared.Expression

sealed class FEREvent {

    data class ChooseDataset(val dirPath: String) : FEREvent()

    data class ChooseTuneExpression(val expression: Expression) : FEREvent()
}