package com.progressterra.ipbandroidview.core.startactivity

import android.content.Intent

interface StartActivityCache : StartActivity {

    fun setListener(listener: StartActivityListener)

    class Base : StartActivityCache {

        private var listener: StartActivityListener? = null

        override fun setListener(listener: StartActivityListener) {
            this.listener = listener
        }

        override fun startActivityFromIntent(intent: Intent) {
            listener?.startActivityFromIntent(intent)
        }
    }
}