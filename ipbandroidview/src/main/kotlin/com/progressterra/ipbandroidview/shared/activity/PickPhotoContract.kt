package com.progressterra.ipbandroidview.shared.activity

import kotlinx.coroutines.CompletableDeferred

interface PickPhotoContract {

    interface Client {

        suspend fun pickPhoto(): String
    }

    interface Listener {

        fun pickPhoto()
    }

    interface Activity {

        fun completePhoto(result: String)

        fun setListener(listener: Listener)
    }

    class Base : Activity, Client {

        private lateinit var listener: Listener

        private var completableDeferred: CompletableDeferred<String>? = null

        override fun setListener(listener: Listener) {
            this.listener = listener
        }

        override suspend fun pickPhoto(): String {
            listener.pickPhoto()
            completableDeferred = CompletableDeferred()
            return completableDeferred!!.await()
        }

        override fun completePhoto(result: String) {
            completableDeferred?.complete(result)
        }
    }
}