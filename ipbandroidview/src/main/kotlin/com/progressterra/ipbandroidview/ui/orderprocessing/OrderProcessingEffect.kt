package com.progressterra.ipbandroidview.ui.orderprocessing

sealed class OrderProcessingEffect {

    object Back : OrderProcessingEffect()

    object Next : OrderProcessingEffect()
}