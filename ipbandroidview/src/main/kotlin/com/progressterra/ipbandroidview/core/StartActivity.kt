package com.progressterra.ipbandroidview.core

import android.content.Intent

interface StartActivity {

    suspend fun start(intent: Intent)

    interface Listener : StartActivity

    interface Activity : StartActivity {


        fun setListener(listener: Listener)
    }

    class Base : Activity {

        private lateinit var listener: Listener

        override fun setListener(listener: Listener) {
            this.listener = listener
        }

        override suspend fun start(intent: Intent) = listener.start(intent)

    }
}