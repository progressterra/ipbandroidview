package com.progressterra.ipbandroidview.pages.documents

import com.progressterra.ipbandroidview.entities.Document
import com.progressterra.ipbandroidview.shared.mvi.OnBack

interface DocumentsScreenNavigation : OnBack {

    fun onDocument(data: Document)
}