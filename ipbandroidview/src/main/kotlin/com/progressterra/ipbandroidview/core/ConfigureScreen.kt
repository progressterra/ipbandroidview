package com.progressterra.ipbandroidview.core

interface ConfigureScreen {

    fun configureScreen(configuration: ScreenConfiguration)

    class Empty : ConfigureScreen {

        override fun configureScreen(configuration: ScreenConfiguration) = Unit
    }
}