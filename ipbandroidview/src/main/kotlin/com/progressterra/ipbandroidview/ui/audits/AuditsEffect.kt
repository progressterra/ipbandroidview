package com.progressterra.ipbandroidview.ui.audits

sealed class AuditsEffect {

    @Suppress("UNUSED_PARAMETER")
    class OnDocumentDetails(document: Document) : AuditsEffect()

    object OnAudit : AuditsEffect()
}
