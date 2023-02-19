package com.progressterra.ipbandroidview.ui.mainhaccp

import com.progressterra.ipbandroidview.model.Document
import com.progressterra.ipbandroidview.model.Partner

sealed class MainHaccpEffect {

    class OpenPartner(val partner: Partner) : MainHaccpEffect()

    class Archive(val title: String, val archived: List<Document>) : MainHaccpEffect()
}
