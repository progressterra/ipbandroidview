package com.progressterra.ipbandroidview.shared

import android.content.Intent
import kotlinx.coroutines.CompletableDeferred

interface MakePhotoContract {

    interface Client {

        suspend fun makePhoto(photoIntent: Intent): Boolean
    }

    interface Listener {

        fun startPhoto(photoIntent: Intent)
    }

    interface Activity {

        fun completePhoto(result: Boolean)

        fun setListener(listener: Listener)
    }

    class Base : Activity, Client {

        private lateinit var listener: Listener

        private var completableDeferred: CompletableDeferred<Boolean>? = null

        override fun setListener(listener: Listener) {
            this.listener = listener
        }

        override suspend fun makePhoto(photoIntent: Intent): Boolean {
            listener.startPhoto(photoIntent)
            completableDeferred = CompletableDeferred()
            return completableDeferred!!.await()
        }

        override fun completePhoto(result: Boolean) {
            completableDeferred?.complete(result)
        }
    }
}