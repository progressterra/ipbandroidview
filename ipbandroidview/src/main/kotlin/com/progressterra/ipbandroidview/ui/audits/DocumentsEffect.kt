package com.progressterra.ipbandroidview.ui.audits

import com.progressterra.ipbandroidview.core.Checklist

sealed class DocumentsEffect {

    @Suppress("unused")
    class OnChecklist(val checklist: Checklist) : DocumentsEffect()

    object OnOrganizations : DocumentsEffect()
}
