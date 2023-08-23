package com.progressterra.ipbandroidview.processes.docs

import com.progressterra.ipbandroidview.entities.Document
import com.progressterra.ipbandroidview.shared.mvi.ModuleUser

interface DocsModuleUser : ModuleUser<Document> {

    fun isValid(isValid: Boolean)

    fun openPhoto(url: String)
}