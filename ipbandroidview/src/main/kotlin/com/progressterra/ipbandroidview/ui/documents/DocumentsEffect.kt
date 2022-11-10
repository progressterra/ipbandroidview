package com.progressterra.ipbandroidview.ui.documents

import com.progressterra.ipbandroidview.model.Checklist

sealed class DocumentsEffect {

    @Suppress("unused")
    class OpenChecklist(val checklist: Checklist) : DocumentsEffect()

    @Suppress("unused")
    class UpdateCounter(val counter: Int) : DocumentsEffect()

    object OpenOrganizations : DocumentsEffect()
}
