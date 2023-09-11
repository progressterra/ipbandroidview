package com.progressterra.ipbandroidview.shared.activity

import androidx.annotation.StringRes

interface MakeToastContract {

    interface Client {

        fun makeToast(@StringRes resId: Int)
    }

    interface Listener {

        fun makeToast(@StringRes resId: Int)
    }

    interface Activity {

        fun setListener(listener: Listener)
    }

    class Base : Activity, Client {

        private lateinit var listener: Listener

        override fun setListener(listener: Listener) {
            this.listener = listener
        }

        override fun makeToast(resId: Int) {
            listener.makeToast(resId)
        }
    }
}