package com.progressterra.ipbandroidview.processes.utils

interface MakeDialogContract {

    interface Client {

        fun start(text: String, action: String, onAction: () -> Unit)
    }

    interface Listener {

        fun start(text: String, action: String, onAction: () -> Unit)
    }

    interface Activity {


        fun setListener(listener: Listener)
    }

    class Base : Activity, Client {

        private lateinit var listener: Listener

        override fun setListener(listener: Listener) {
            this.listener = listener
        }

        override fun start(text: String, action: String, onAction: () -> Unit) = listener.start(text, action, onAction)

    }
}