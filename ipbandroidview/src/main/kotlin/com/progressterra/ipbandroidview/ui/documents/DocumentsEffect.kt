package com.progressterra.ipbandroidview.ui.documents

import com.progressterra.ipbandroidview.core.Checklist

sealed class DocumentsEffect {

    @Suppress("unused")
    class OnChecklist(val checklist: Checklist) : DocumentsEffect()

    class UpdateCounter(val counter: Int) : DocumentsEffect()

    object OnOrganizations : DocumentsEffect()
}
