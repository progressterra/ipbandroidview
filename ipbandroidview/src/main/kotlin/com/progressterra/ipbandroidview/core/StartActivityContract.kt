package com.progressterra.ipbandroidview.core

import android.content.Intent

interface StartActivityContract {

    interface Client {

        fun start(intent: Intent)
    }

    interface Listener {

        fun start(intent: Intent)
    }

    interface Activity {


        fun setListener(listener: Listener)
    }

    class Base : Activity, Client {

        private lateinit var listener: Listener

        override fun setListener(listener: Listener) {
            this.listener = listener
        }

        override fun start(intent: Intent) = listener.start(intent)

    }
}