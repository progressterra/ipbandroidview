package com.progressterra.ipbandroidview.processes.utils


interface ManagePermissionContract {

    interface Client {

        fun requestPermission(permission: String)

        fun checkPermission(permission: String): Boolean
    }

    interface Listener {

        fun requestPermission(permission: String)

        fun checkPermission(permission: String): Boolean
    }

    interface Activity {

        fun setListener(listener: Listener)
    }

    class Base : Activity, Client {

        private lateinit var listener: Listener

        override fun setListener(listener: Listener) {
            this.listener = listener
        }

        override fun requestPermission(permission: String) = listener.requestPermission(permission)


        override fun checkPermission(permission: String): Boolean =
            listener.checkPermission(permission)
    }
}