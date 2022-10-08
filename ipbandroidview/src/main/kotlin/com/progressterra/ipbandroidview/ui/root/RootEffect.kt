package com.progressterra.ipbandroidview.ui.root

sealed class RootEffect {

    object OnAudits : RootEffect()

    object OnOrganizations : RootEffect()

    object OnProfile : RootEffect()
}
