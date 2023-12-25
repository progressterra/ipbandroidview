package com.progressterra.ipbandroidview.pages.checklist

sealed class ChecklistScreenEffect {

    data object OnBack : ChecklistScreenEffect()

    data class OnImage(val picture: String) : ChecklistScreenEffect()
}