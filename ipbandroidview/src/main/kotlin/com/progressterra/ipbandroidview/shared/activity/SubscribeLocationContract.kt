package com.progressterra.ipbandroidview.shared.activity

import android.location.Location
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableSharedFlow

interface SubscribeLocationContract {

    interface Client {

        suspend fun start()

        suspend fun stop()

        val resultFlow: Flow<Result<Location>>
    }

    interface Listener {

        fun start()

        fun stop()
    }

    interface Activity {

        suspend fun onNewLocation(newLocation: Result<Location>)

        fun setListener(listener: Listener)
    }

    class Base : Activity, Client {

        private lateinit var listener: Listener

        override fun setListener(listener: Listener) {
            this.listener = listener
        }

        override suspend fun start() {
            listener.start()
        }

        override suspend fun stop() {
            listener.stop()
        }

        private val _resultFlow = MutableSharedFlow<Result<Location>>()
        override val resultFlow: Flow<Result<Location>> = _resultFlow

        override suspend fun onNewLocation(newLocation: Result<Location>) {
            _resultFlow.emit(newLocation)
        }
    }
}