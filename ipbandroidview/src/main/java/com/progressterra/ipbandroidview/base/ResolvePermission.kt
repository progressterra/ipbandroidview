package com.progressterra.ipbandroidview.base


interface ResolvePermission : ManagePermission {

    fun setListener(listener: PermissionListener)

    class Base : ResolvePermission {

        private var listener: PermissionListener? = null

        override fun setListener(listener: PermissionListener) {
            this.listener = listener
        }

        override fun requirePermission(permission: String) {
            listener?.requirePermission(permission)
        }

        override fun checkPermission(permission: String): Boolean =
            listener?.checkPermission(permission) ?: false
    }
}