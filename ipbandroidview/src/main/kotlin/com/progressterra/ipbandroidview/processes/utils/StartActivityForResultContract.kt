package com.progressterra.ipbandroidview.processes.utils

import android.content.Intent
import kotlinx.coroutines.CompletableDeferred

interface StartActivityForResultContract {

    interface Client {

        suspend fun startForResult(intent: Intent, code: Int): Triple<Int, Int, Intent?>
    }

    interface Listener {

        fun startForResult(intent: Intent, code: Int)
    }

    interface Activity {

        fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?)

        fun setListener(listener: Listener)
    }

    class Base : Activity, Client {

        private lateinit var listener: Listener

        private var completableDeferred: CompletableDeferred<Triple<Int, Int, Intent?>>? = null

        override fun setListener(listener: Listener) {
            this.listener = listener
        }

        override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
            completableDeferred?.complete(Triple(requestCode, resultCode, data))
        }

        override suspend fun startForResult(intent: Intent, code: Int): Triple<Int, Int, Intent?> {
            listener.startForResult(intent, code)
            completableDeferred = CompletableDeferred()
            return completableDeferred!!.await()
        }
    }
}