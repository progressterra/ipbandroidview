package com.progressterra.ipbandroidview.core


interface ManagePermission {

    suspend fun requestPermission(permission: String): Boolean

    suspend fun checkPermission(permission: String): Boolean

    interface Listener : ManagePermission

    interface Activity : ManagePermission {

        suspend fun setListener(listener: Listener)
    }

    class Base : Activity {

        private lateinit var listener: Listener

        override suspend fun setListener(listener: Listener) {
            this.listener = listener
        }

        override suspend fun requestPermission(permission: String): Boolean =
            listener.requestPermission(permission)


        override suspend fun checkPermission(permission: String): Boolean =
            listener.checkPermission(permission)
    }
}