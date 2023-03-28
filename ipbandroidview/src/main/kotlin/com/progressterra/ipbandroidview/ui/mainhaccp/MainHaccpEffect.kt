package com.progressterra.ipbandroidview.ui.mainhaccp

import com.progressterra.ipbandroidview.entities.Document
import com.progressterra.ipbandroidview.entities.Partner

sealed class MainHaccpEffect {

    class OpenPartner(val partner: Partner) : MainHaccpEffect()

    class Archive(val title: String, val archived: List<Document>) : MainHaccpEffect()
}
