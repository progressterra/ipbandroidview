package com.progressterra.ipbandroidview.ui.pickuppoint

import com.progressterra.ipbandroidview.model.delivery.PickUpPointInfo

interface PickUpPointInteractor {

    fun onBack()

    fun choose(info: PickUpPointInfo)

    fun onNext()

    class Empty : PickUpPointInteractor {

        override fun onBack() = Unit

        override fun onNext() = Unit

        override fun choose(info: PickUpPointInfo) = Unit
    }
}