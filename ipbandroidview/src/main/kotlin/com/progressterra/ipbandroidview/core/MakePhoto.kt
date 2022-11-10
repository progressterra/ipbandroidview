package com.progressterra.ipbandroidview.core

import android.content.Intent

interface MakePhoto {

    suspend fun makePhoto(photoIntent: Intent): Boolean

    interface Listener : MakePhoto

    interface Activity : MakePhoto {


        fun setListener(listener: Listener)
    }

    class Base : Activity {

        private lateinit var listener: Listener

        override fun setListener(listener: Listener) {
            this.listener = listener
        }

        override suspend fun makePhoto(photoIntent: Intent): Boolean =
            listener.makePhoto(photoIntent)

    }
}