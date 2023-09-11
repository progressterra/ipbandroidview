package com.progressterra.ipbandroidview.shared.activity

import kotlinx.coroutines.CompletableDeferred

interface PickDateContract {

    interface Client {

        suspend fun pickDate(): String
    }

    interface Listener {

        fun startDatePick()

    }

    interface Activity {

        fun completeDatePick(result: String)

        fun setListener(listener: Listener)
    }

    class Base : Activity, Client {

        private lateinit var listener: Listener

        private var completableDeferred: CompletableDeferred<String>? = null

        override fun setListener(listener: Listener) {
            this.listener = listener
        }

        override suspend fun pickDate(): String {
            listener.startDatePick()
            completableDeferred = CompletableDeferred()
            return completableDeferred!!.await()
        }

        override fun completeDatePick(result: String) {
            completableDeferred?.complete(result)
        }
    }
}