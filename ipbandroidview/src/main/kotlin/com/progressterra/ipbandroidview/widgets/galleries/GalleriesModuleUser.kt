package com.progressterra.ipbandroidview.widgets.galleries

import com.progressterra.ipbandroidview.shared.mvi.ModuleUser

interface GalleriesModuleUser : ModuleUser<GalleriesState> {

    fun onGoods(data: String)

    fun onAuth()
}