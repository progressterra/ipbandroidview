package com.progressterra.ipbandroidview.ui.audits

sealed class AuditsEffect {

    @Suppress("UNUSED_PARAMETER")
    class OnDocumentDetails(val document: Document) : AuditsEffect()

    object OnOrganizations : AuditsEffect()
}
