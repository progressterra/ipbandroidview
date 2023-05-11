package com.progressterra.ipbandroidview.ui.orderprocessing

interface OrderProcessingInteractor {

    fun onNext()

    fun onBack()

    class Empty : OrderProcessingInteractor {

        override fun onNext() = Unit

        override fun onBack() = Unit
    }
}