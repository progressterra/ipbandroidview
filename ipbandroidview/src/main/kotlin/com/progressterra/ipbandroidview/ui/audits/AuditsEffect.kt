package com.progressterra.ipbandroidview.ui.audits

sealed class AuditsEffect {

    @Suppress("UNUSED_PARAMETER")
    class OnDocumentDetails(id: String) : AuditsEffect()

    object OnAudit : AuditsEffect()
}
