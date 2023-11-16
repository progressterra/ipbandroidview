package com.progressterra.ipbandroidview.processes.media

import android.net.Uri
import kotlinx.coroutines.CompletableDeferred

interface PickPhotoContract {

    interface Client {

        suspend fun pickPhoto(): Uri?
    }

    interface Listener {

        fun pickPhoto()
    }

    interface Activity {

        fun completePhoto(result: Uri?)

        fun setListener(listener: Listener)
    }

    class Base : Activity, Client {

        private lateinit var listener: Listener

        private var completableDeferred: CompletableDeferred<Uri?>? = null

        override fun setListener(listener: Listener) {
            this.listener = listener
        }

        override suspend fun pickPhoto(): Uri? {
            listener.pickPhoto()
            completableDeferred = CompletableDeferred()
            return completableDeferred!!.await()
        }

        override fun completePhoto(result: Uri?) {
            completableDeferred?.complete(result)
        }
    }
}